package javafoodapp.controller;

import javafoodapp.database.dao.auth.ImplementAuth;

import javax.swing.*;

public class AuthController {
    private final JFrame frame;
    private final ImplementAuth implementAuth;

    public AuthController(JFrame frame, ImplementAuth implementAuth) {
        this.frame = frame;
        this.implementAuth = implementAuth;
    }
}
