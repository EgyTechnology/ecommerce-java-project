package edu.ndeti.procoders;

import edu.ndeti.procoders.exceptions.DuplicatedUsernameException;
import edu.ndeti.procoders.models.Client;
import edu.ndeti.procoders.models.Manager;
import edu.ndeti.procoders.models.User;
import edu.ndeti.procoders.repositories.UsersRepository;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel {
    public JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JList usersList;
    private JButton saveButton;
    private JButton cleanButton;
    private JButton deleteButton;
    private JTextField firstNameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField passwordConfirmField;
    private JCheckBox isManagerCheckBox;
    private JTextField middleNameField;
    private JTextField lastNameField;

    private User user;

    private DefaultListModel<User> usersListModel = new DefaultListModel<>();

    public MainPanel() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isManagerCheckBox.isSelected()) {
                    user = new Manager();
                } else {
                    user = new Client();
                }

                user.setUsername(usernameField.getText());
                user.setFirstName(firstNameField.getText());
                user.setMiddleName(middleNameField.getText());
                user.setLastName(lastNameField.getText());
                user.setPasswordHash(passwordField.getSelectedText());

                try {
                    UsersRepository.getInstance().addUser(user);

                    usersListModel.addElement(user);

                    usersList.setModel(usersListModel);

                    cleanAllFields();
                } catch (DuplicatedUsernameException exception) {
                    JOptionPane.showMessageDialog(null, "There is another user with same username, please select another one", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanAllFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });

        usersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selectedItem = (User) usersList.getSelectedValue();

                if (selectedItem instanceof User) {
                    user = (User) selectedItem;
                    setFieldValues(user);
                }
            }
        });
    }

    private void removeUser() {
        UsersRepository.getInstance().removeUser(user);
        usersListModel.removeElement(user);
        usersList.setModel(usersListModel);
        user = null;
        cleanAllFields();
    }

    private void setFieldValues(User user) {
        this.firstNameField.setText(user.getFirstName());
        this.middleNameField.setText(user.getMiddleName());
        this.lastNameField.setText(user.getLastName());
        this.usernameField.setText(user.getUsername());
        this.passwordField.setText("");
        this.passwordConfirmField.setText("");
        this.isManagerCheckBox.setSelected(user.isManager());
    }

    private void cleanAllFields() {
        this.firstNameField.setText("");
        this.middleNameField.setText("");
        this.lastNameField.setText("");
        this.usernameField.setText("");
        this.passwordField.setText("");
        this.passwordConfirmField.setText("");
        this.isManagerCheckBox.setSelected(false);
    }
}
