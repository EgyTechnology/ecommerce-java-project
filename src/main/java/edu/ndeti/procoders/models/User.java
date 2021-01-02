package edu.ndeti.procoders.models;

import edu.ndeti.procoders.exceptions.InvalidPasswordException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class User extends UniqueItem {
    private String firstName;
    private String middleName = "";
    private String lastName = "";
    private String username;
    private String passwordHash;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        if (this.passwordHash == null) {
            this.passwordHash = hash(password);
        }
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public boolean isManager() {
        if (this instanceof Manager)
            return true;

        return false;
    }

    public void changePassword(String oldPassword, String newPassword) throws InvalidPasswordException {
        if (!isValidPassword(oldPassword))
            throw new InvalidPasswordException();

        passwordHash = hash(newPassword);
    }

    private String hash(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md5.digest();
            return new String(digest, StandardCharsets.UTF_8).toUpperCase();
        } catch (NoSuchAlgorithmException exception) {
            return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        }
    }

    public Boolean isValidPassword(String password) {
        String hashedPassword = hash(password);
        return passwordHash.equals(hashedPassword) ? true : false;
    }

    @Override
    public String toString() {
        String roleLabel = isManager() ? "Manager" : "Client";
        return getFullName() + " (" + roleLabel + ")";
    }
}
