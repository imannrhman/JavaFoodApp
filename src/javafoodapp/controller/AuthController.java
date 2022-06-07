package javafoodapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javafoodapp.component.Message;
import javafoodapp.component.PanelCover;
import javafoodapp.component.PanelLoading;
import javafoodapp.component.PanelLoginAndRegister;
import javafoodapp.database.dao.auth.AuthDAO;
import javafoodapp.database.dao.auth.ImplementAuth;
import javafoodapp.database.dao.role.ImplementRole;
import javafoodapp.database.dao.role.RoleDAO;
import javafoodapp.model.Auth;
import javafoodapp.model.Role;
import javafoodapp.model.User;
import javafoodapp.swing.ComboBoxSuggestion;
import javafoodapp.view.Dashboard;
import javafoodapp.view.Login;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class AuthController {

   private final Login authForm;
   private final ImplementAuth implementAuth;
   private final ImplementRole implementRole;
   private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
   private MigLayout layout;
   private PanelCover cover;
   private PanelLoading loading;
   private PanelLoginAndRegister loginAndRegister;
   private boolean isLogin;
   private final double addSize=40;
   private final double coverSize=40;
   private final double loginSize= 60;

   public AuthController(Login authForm) {
        this.authForm = authForm;
        implementAuth = new AuthDAO();
        implementRole = new RoleDAO();
   }
    
    
    public void createAnimation() {
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
        setRoleComboBox();


        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                authForm.background().revalidate();
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

        authForm.background().setLayout(layout);

        authForm.background().setLayer(loading, JLayeredPane.POPUP_LAYER);
        authForm.background().add(loading, "pos 0 0 100% 100%");
        authForm.background().add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        authForm.background().add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%"); //  1al as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }



    private void register() {

        Auth user = loginAndRegister.getAuth();
        try {
           if (user.getPassword().equals(user.getConfirmPassword())) {
               implementAuth.register(user);
               showMessage(Message.MessageType.SUCCESS, "Registrasi berhasil !");
           } else {
               showMessage(Message.MessageType.ERROR, "Password Tidak Sama");
           }
        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Register");
        }
    }

    private void login() {
        Auth auth = loginAndRegister.getAuth();
        try {
            User user = implementAuth.login(auth.getUsername(), auth.getPassword());
            if (user != null) {
                sendDashboard(user.getId());
            } else {
                showMessage(Message.MessageType.ERROR, "Email and Password incorrect");
            }

        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
        }
    }

    private void sendDashboard(int idUser) {
        authForm.setVisible(false);
        new Dashboard(idUser).setVisible(true);
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    authForm.background().add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    authForm.background().repaint();
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
                authForm.background().repaint();
                authForm.background().revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    authForm.background().remove(ms);
                    authForm.background().repaint();
                    authForm.background().revalidate();
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


    public void setRoleComboBox() {
      try {
          List<Role> roleList = implementRole.getAllRole();
          ComboBoxSuggestion<String> comboBoxSuggestion = loginAndRegister.getRole();
          for (Role role: roleList) {
              comboBoxSuggestion.addItem(role.getName());
          }
          loginAndRegister.setRole(comboBoxSuggestion);
      } catch (Exception e) {
          System.out.println(e);
      }
    }


}
