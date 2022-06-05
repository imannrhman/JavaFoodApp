package javafoodapp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javafoodapp.component.*;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;

/**
 *
 * @author imannrhman
 */
public class Login extends javax.swing.JFrame {

   private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
   private MigLayout layout;
   private PanelCover cover;
   private PanelLoading loading;
   private PanelLoginAndRegister loginAndRegister;
   private boolean isLogin;
   private final double addSize=40;
   private final double coverSize=40;
   private final double loginSize= 60;

    private  void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };


        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };

        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin);

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size=coverSize;

                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;

                }

                if(isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                }
                fractionCover = Double.parseDouble(df.format(fractionCover));
                fractionLogin = Double.parseDouble(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");

                background.revalidate();

            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
            
            
        };
        Animator animator = new Animator(1000, target);
        animator.setAcceleration(0.5f);
        animator.setAcceleration(0.5f);
        animator.setResolution(0); //For smooth animation

        background.setLayout(layout);
        background.setLayer(loading, JLayeredPane.POPUP_LAYER);
        background.add(loading, "pos 0 0 100% 100%");
        background.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        background.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%"); //  1al as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });

    }

    public Login() {
        initComponents();
        init();
    }

    private void register() {
//        ModelUser user = loginAndRegister.getUser();
//        try {
//            if (service.checkDuplicateUser(user.getUserName())) {
//                showMessage(Message.MessageType.ERROR, "User name already exit");
//            } else if (service.checkDuplicateEmail(user.getEmail())) {
//                showMessage(Message.MessageType.ERROR, "Email already exit");
//            } else {
//                service.insertUser(user);
//                sendMain(user);
//            }
//        } catch (SQLException e) {
//            showMessage(Message.MessageType.ERROR, "Error Register");
//        }
    }

    private void login() {
//        ModelLogin data = loginAndRegister.getDataLogin();
//        try {
//            ModelUser user = service.login(data);
//            if (user != null) {
//                this.dispose();
//                MainSystem.main(user);
//            } else {
//                showMessage(Message.MessageType.ERROR, "Email and Password incorrect");
//            }
//
//        } catch (SQLException e) {
//            showMessage(Message.MessageType.ERROR, "Error Login");
//        }
    }

    private void sendMain() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                loading.setVisible(true);
//                ModelMessage ms = new ServiceMail().sendMain(user.getEmail(), user.getVerifyCode());
//                if (ms.isSuccess()) {
//                    loading.setVisible(false);
//                    verifyCode.setVisible(true);
//                } else {
//                    loading.setVisible(false);
//                    showMessage(Message.MessageType.ERROR, ms.getMessage());
//                }
//            }
//        }).start();
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    background.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    background.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                background.repaint();
                background.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    background.remove(ms);
                    background.repaint();
                    background.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 994, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Flatlaf Light".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    // End of variables declaration//GEN-END:variables
}
