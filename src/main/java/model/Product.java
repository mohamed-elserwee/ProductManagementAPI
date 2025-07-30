package model;

public class Product {
    int id;
    String name;
    int weight;
    double price;

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Product(String name, int weight, double price, int id) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }



}
