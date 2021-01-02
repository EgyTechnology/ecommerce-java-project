package edu.ndeti.procoders.repositories;

import edu.ndeti.procoders.models.Product;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductsRepository {
    private static ProductsRepository repositoryInstance = null;

    private List<Product> products;

    private ProductsRepository() {
        products = new ArrayList<>();
        
        final Product dummyProduct = new Product();
        dummyProduct.setIdentifier(UUID.randomUUID().toString());
        dummyProduct.setName("Dummy Product");
        dummyProduct.setDescription("Test Product");
        dummyProduct.setPrice(99.9999);
        dummyProduct.setImage(new BufferedImage(145, 145, IndexColorModel.TRANSLUCENT));
        
        addProduct(dummyProduct);
        
        final Product dummyProduct1 = new Product();
        dummyProduct1.setIdentifier(UUID.randomUUID().toString());
        dummyProduct1.setName("Dummy Product 1");
        dummyProduct1.setDescription("Test Product 1");
        dummyProduct1.setPrice(1200.0);
        dummyProduct1.setImage(new BufferedImage(145, 145, IndexColorModel.TRANSLUCENT));
        
        addProduct(dummyProduct1);
        
        final Product dummyProduct2 = new Product();
        dummyProduct2.setIdentifier(UUID.randomUUID().toString());
        dummyProduct2.setName("Dummy Product 2");
        dummyProduct2.setDescription("Test Product 2");
        dummyProduct2.setPrice(1.5);
        dummyProduct2.setImage(new BufferedImage(145, 145, IndexColorModel.TRANSLUCENT));
        
        addProduct(dummyProduct2);
        
    }

    public static ProductsRepository getInstance() {
        if (repositoryInstance == null)
            repositoryInstance = new ProductsRepository();

        return repositoryInstance;
    }

    public void addProduct(Product product) {
        product.setIdentifier(UUID.randomUUID().toString());

        products.add(product);
    }

    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

    public boolean removeProduct(String identifier) {
        Optional<Product> productObj = findProductByIdentifier(identifier);

        if (!productObj.isPresent())
            return false;

        return removeProduct(productObj.get());
    }

    public Optional<Product> findProductByIdentifier(String identifier) {
        return products.stream().filter(u -> u.getIdentifier().equals(identifier)).findFirst();
    }

    public List<Product> getProducts() {
        return products;
    }
}
