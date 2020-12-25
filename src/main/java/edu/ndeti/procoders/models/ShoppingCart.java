package edu.ndeti.procoders.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    public Client client;
    private List<CartItem> itemsList;

    public ShoppingCart(Client client) {
        this.client = client;
        itemsList = new ArrayList<>();
    }

    public void addItem(Product product) {
        Optional<CartItem> findCartItem = findProduct(product);

        if (findCartItem.isPresent()) {
            findCartItem.get().increaseCount();
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);

            itemsList.add(newItem);
        }
    }

    public boolean removeItem(Product product) {
        Optional<CartItem> findCartItem = findProduct(product);

        if (!findCartItem.isPresent())
            return false;

        return itemsList.remove(findCartItem.get());
    }

    public Optional<CartItem> findProduct(Product product) {
        return itemsList.stream().filter(i -> i.getProduct().equals(product)).findFirst();
    }

    public List<CartItem> getItemsList() {
        return itemsList;
    }
}
