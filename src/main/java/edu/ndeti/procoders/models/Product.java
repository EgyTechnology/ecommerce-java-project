package edu.ndeti.procoders.models;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Product extends UniqueItem {
    private String name;
    private String description;
    private Double price;
    private BufferedImage image;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name + " $" + price + " (" + this.getIdentifier() + ")";
    }
}
