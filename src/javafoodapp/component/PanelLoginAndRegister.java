package javafoodapp.component;

import javafoodapp.model.Auth;
import javafoodapp.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {


    private Auth auth;
    private ComboBoxSuggestion<String> roleComboBox;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public ComboBoxSuggestion<String> getRole() {
        return roleComboBox;
    }

    public void setRole(ComboBoxSuggestion<String> role) {
        this.roleComboBox = role;
    }

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]25[]25[]push"));
        JLabel label = new JLabel("Buat Akun");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);

        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/user.png")));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");

        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/user.png")));
        txtEmail.setHint("Username");
        register.add(txtEmail, "w 60%");

        MyTextField txtPhone = new MyTextField();
        txtPhone.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/phone.png")));
        txtPhone.setHint("Nomor Telepon");
        register.add(txtPhone, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");

        MyPasswordField txtConfirmPassword = new MyPasswordField();
        txtConfirmPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/pass.png")));
        txtConfirmPassword.setHint("Konfirmasi Password");
        register.add(txtConfirmPassword, "w 60%");

        roleComboBox = new ComboBoxSuggestion<String>();
        roleComboBox.setEditable(false);
        register.add(roleComboBox, "w 59%, h 40");

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("DAFTAR");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Auth authData = new Auth();
                authData.setFullName(txtUser.getText().trim());
                authData.setUsername(txtEmail.getText().trim());
                authData.setPassword(String.valueOf(txtPass.getPassword()));
                authData.setPhoneNumber(txtPhone.getText().trim());
                authData.setRoleName(String.valueOf(roleComboBox.getSelectedItem()));
                authData.setConfirmPassword(String.valueOf(txtPass.getPassword()));
                auth = authData;

            }
        });
    }

    private void initLogin(ActionListener eventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]25[]push"));
        JLabel label = new JLabel("LOGIN");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/user.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/javafoodapp/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogin);
        cmd.setText("LOGIN");
        login.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Auth authData = new Auth();
                authData.setUsername(txtEmail.getText().trim());
                authData.setPassword(String.valueOf(txtPass.getPassword()));
                auth = authData;
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
