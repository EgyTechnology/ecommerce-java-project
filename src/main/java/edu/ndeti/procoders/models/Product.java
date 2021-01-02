package edu.ndeti.procoders.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;

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
    
    public java.util.Hashtable<String, Object> toHashTable() throws IllegalAccessException {
        final Field[] productFields = getClass().getDeclaredFields();
        final Field[] superClassFields = getClass().getSuperclass().getDeclaredFields();
        
        final Hashtable<String, Object> table = new Hashtable<>();
        
        for (int i = 0; i < superClassFields.length; i++) {
            table.put(superClassFields[i].getName(), superClassFields[i].get(this));
        }
        
        for (int i = 0; i < productFields.length; i++) {
            table.put(productFields[i].getName(), productFields[i].get(this));
        }
        
        return table;
    }
}
