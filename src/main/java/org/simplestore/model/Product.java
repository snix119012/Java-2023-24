package org.simplestore.model;

public class Product {
    private final int id;
    private final String name;
    private final double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Integer getId() {return id;}
    public String getName() {return name;}
    public double getPrice() {return price;}
    public String toString(){
        return "Product{id="+id+", name='"+name+"', price="+price+"}";
    }
}