package edu.ndeti.procoders.repositories;

import edu.ndeti.procoders.models.Product;
import edu.ndeti.procoders.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductsRepository {
    private static ProductsRepository repositoryInstance = null;

    private List<Product> products;

    private ProductsRepository() {
        products = new ArrayList<>();
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
