package edu.ndeti.procoders.models;

import java.util.Optional;

public class CartItem {
    private Product product;
    private int count = 0;

    private ShoppingCart cart;

    public void setProduct(Product product) {
        this.product = product;

        if (count == 0)
            count = 1;
    }

    public Product getProduct() {
        return product;
    }

    public void increaseCount() {
        count = count + 1;
    }

    public void increaseCount(int count) {
        this.count = this.count + count;
    }

    public void decreaseCount() {
        count = count - 1;
    }

    public void decreaseCount(int count) {
        int reminder = this.count - count;

        if (reminder > 0)
            this.count = reminder;
        else
            cart.removeItem(product); // Remove the item if count is less or equal to 0
    }
}
