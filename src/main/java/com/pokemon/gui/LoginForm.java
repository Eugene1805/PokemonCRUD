package com.pokemon.gui;

import dao.PokemonDAO;
import dao.UserDAO;
import dao.UserDAOImp;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginForm() {
        setTitle("Inicio de Sesion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton loginButton = new JButton("Iniciar Sesion");
        loginButton.addActionListener(new LoginAction());

        panel.add(new JLabel("User:", SwingConstants.CENTER));
        panel.add(usernameField);
        panel.add(new JLabel("Password:", SwingConstants.CENTER));
        panel.add(passwordField);

        add(panel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                messageLabel.setText("Inicio de sesión exitoso");
                JOptionPane.showMessageDialog(LoginForm.this, "Bienvenido " + username);
                dispose();
                new PokemonManager();
            } else {
                messageLabel.setText("Usuario o contraseña incorrectos");
            }
        }

        private boolean authenticate(String username, String password) {
            UserDAO userDao = new UserDAOImp();
            String user;
            String pass;
            try {
                User dbUser = userDao.readUser(1);
                user = dbUser.getUser();
                pass = dbUser.getPassword();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return user.equals(username) && pass.equals(password);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }
}

