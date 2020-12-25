package edu.ndeti.procoders;

import edu.ndeti.procoders.repositories.UsersRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class App extends JFrame {
    private LoginPanel loginPanel = new LoginPanel();
    private MainPanel mainPanel = new MainPanel();

    private App() {
        super("E-Commerce");

        add(loginPanel.panel1);
        add(mainPanel.panel1);
        setContentPane(mainPanel.panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000, 500);
        pack();
        setVisible(true);

        loginPanel.loginButton.addActionListener(e -> {
            this.setContentPane(mainPanel.panel1);
        });
    }

    public static void main(String[] args) {
        UsersRepository.getInstance();

        new App();
    }
}
