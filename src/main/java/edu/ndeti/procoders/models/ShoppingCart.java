package edu.ndeti.procoders.models;

import edu.ndeti.procoders.models.CartItem;
import edu.ndeti.procoders.models.Client;
import edu.ndeti.procoders.models.Product;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ShoppingCart extends JPanel {
    public Client client;
    private Hashtable<String, CartItem> itemsTable;
    
    public ShoppingCart(Client client) {
        this.client = client;
        itemsTable = new Hashtable<>();
    }

    public void addItem(Product product) {
        Optional<CartItem> findCartItem = findItem(product);

        if (findCartItem.isPresent()) {
            findCartItem.get().increaseCount();
        } else {
            CartItem newItem = new CartItem(this);
            newItem.setProduct(product);

            addSubPanel(product);
        }
    }
    
    private void addSubPanel(Product product) {
        final CartItem cartItemPanel = new CartItem(this, product);
        
        itemsTable.put(product.getIdentifier(), cartItemPanel);
        
        add(cartItemPanel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refreshShoppingItemsPanel();
    }
    
    private void removeSubPanel(String productIdenetifer) throws NullPointerException {
        final CartItem removedCartItemPanel = itemsTable.remove(productIdenetifer);
        this.remove(removedCartItemPanel);
        refreshShoppingItemsPanel();
    }
    
    private void refreshShoppingItemsPanel() {
        this.revalidate();
        this.repaint();
    }
    
    public boolean removeItem(Product product) {
        try {
            removeSubPanel(product.getIdentifier());
            return true;
        } catch (NullPointerException exception) {
            return false;
        }
    }

    @Override
    public void removeAll() {
        itemsTable.clear();
        
        super.removeAll(); //To change body of generated methods, choose Tools | Templates.
    
        refreshShoppingItemsPanel();
    }

    public Optional<CartItem> findItem(Product product) {
        return Optional.ofNullable(itemsTable.get(product.getIdentifier()));
    }

    public boolean isItemExists(Product product) {
        return itemsTable.containsKey(product.getIdentifier());
    }
    
    public Collection<CartItem> getItemsList() {
        return itemsTable.values();
    }
    
    public Double getTotalCartValue() {
        return getItemsList().stream()
                .map(i -> i.getTotalItemValue())
                .reduce(0.0, (subTotal, i) -> subTotal + i);
    }
}
