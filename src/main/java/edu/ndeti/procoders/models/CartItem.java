package edu.ndeti.procoders.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartItem extends javax.swing.JPanel {
    private Product product;
    private int count = 0;

    private ShoppingCart cartPanel;

    public CartItem(ShoppingCart cartPanel) { 
        this.cartPanel = cartPanel;
        
        initComponents();
    }
    
    public CartItem(ShoppingCart cartPanel, Product product) {
        this.cartPanel = cartPanel;
     
        setProduct(product);
        
        initComponents();
        refreshComponentValues();
    }
    
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
        refreshComponentValues();
    }

    public void increaseCount(int count) {
        this.count = this.count + count;
        refreshComponentValues();
    }

    public void decreaseCount() {
        count = count - 1;
        refreshComponentValues();
    }

    public void decreaseCount(int count) {
        int reminder = this.count - count;

        if (reminder > 0) {
            this.count = reminder;
            refreshComponentValues();
        } else {
            cartPanel.removeItem(product); // Remove the item if count is less or equal to 0
        }
    }
    
    public int getCount() {
        return count;
    }
    
    public Double getTotalItemValue() {
        return getCount() * product.getPrice();
    }
    
    private void initComponents() {

        productNameLabel = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        removeProductButton = new javax.swing.JToggleButton();

        setLayout(new java.awt.GridLayout());

        productNameLabel.setText("Product Name");
        add(productNameLabel);
        add(quantityTextField);

        removeProductButton.setText("Remove");
        removeProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeProductButtonActionPerfomed(evt);
            }
        });
        add(removeProductButton);
        
        revalidate();
        repaint();
    }
    
    private void refreshComponentValues() {
        productNameLabel.setText(this.getProduct().getName());
        quantityTextField.setText(this.getCount() + "");
    }
    
    private void removeProductButtonActionPerfomed(ActionEvent evt) {
        cartPanel.removeItem(product);
    }
    
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JToggleButton removeProductButton;
}
