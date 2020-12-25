package edu.ndeti.procoders.repositories;

import edu.ndeti.procoders.exceptions.DuplicatedUsernameException;
import edu.ndeti.procoders.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsersRepository {
    private static UsersRepository repositoryInstance = null;

    private List<User> users;

    private UsersRepository() {
        users = new ArrayList();
    }

    public static UsersRepository getInstance() {
        if (repositoryInstance == null)
            repositoryInstance = new UsersRepository();

        return repositoryInstance;
    }

    public void addUser(User user) throws DuplicatedUsernameException {
        // Set Identifier for user
        Optional<User> userObj = findUserByUsername(user.getUsername());

        if (userObj.isPresent())
            throw new DuplicatedUsernameException();

        user.setIdentifier(UUID.randomUUID().toString());

        // Add to list
        users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public boolean removeUser(String identifier) {
        Optional<User> userObj = findUserByIdentifier(identifier);

        if (!userObj.isPresent())
            return false;

        return removeUser(userObj.get());
    }

    public Optional<User> findUserByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findUserByIdentifier(String identifier) {
        return users.stream().filter(u -> u.getIdentifier().equals(identifier)).findFirst();
    }

    public List<User> getUsers() {
        return users;
    }
}
