package service;

import dao.DataBaseAccess;
import model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private final DataBaseAccess dao;

    public ProductService(DataBaseAccess dao) {
        this.dao = dao;
    }

    public Product findByName(String name) {
        return dao.listOfAllProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Product findById(int id) {
        return dao.listOfAllProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getAllProducts() {
        return dao.listOfAllProducts();
    }

    public List<Product> getProductsCheaperThan(double price) {
        return dao.listOfAllProducts().stream()
                .filter(p -> p.getPrice() < price)
                .collect(Collectors.toList());
    }

    public void deleteByName(String name) {
        Product target = findByName(name);
        if (target != null) {
            dao.deleteProductById(target.getId());
        }
    }

    public void updatePriceByName(String name, double newPrice) {
        Product target = findByName(name);
        if (target != null) {
            dao.updateProduct(target.getId(), target.getName(), target.getWeight(), newPrice);
        }
    }
}

