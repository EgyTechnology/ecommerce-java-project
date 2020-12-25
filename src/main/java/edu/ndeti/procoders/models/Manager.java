package edu.ndeti.procoders.models;

import edu.ndeti.procoders.exceptions.DuplicatedUsernameException;
import edu.ndeti.procoders.repositories.ProductsRepository;
import edu.ndeti.procoders.repositories.UsersRepository;

public class Manager extends User {

    public void addUser(User user) throws DuplicatedUsernameException {
        UsersRepository.getInstance().addUser(user);
    }

    public void addProduct(Product product) {
        ProductsRepository.getInstance().addProduct(product);
    }
}
