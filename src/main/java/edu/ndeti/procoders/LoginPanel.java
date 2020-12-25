package edu.ndeti.procoders;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {
    private JPasswordField passwordField;
    public JPanel panel1;
    private JTextField usernameField;
    public JButton loginButton;

    public LoginPanel() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String username = usernameField.getText();

                JOptionPane.showMessageDialog(null, "Hello " + username);
            }
        });
    }
}
