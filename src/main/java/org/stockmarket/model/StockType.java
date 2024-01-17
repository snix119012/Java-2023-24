package org.stockmarket.model;

public enum StockType {
    TECHNOLOGY("Technology", "Technology and innovation sector"),
    FINANCE("Finance", "Banking and financial services"),
    HEALTHCARE("Healthcare", "Medical and healthcare services"),
    CONSUMER_GOODS("Consumer Goods", "Goods for personal and household use"),
    ENERGY("Energy", "Energy production and utilities");

    private final String name;
    private final String description;
    StockType(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getName() {return name;}
    public String getDescription() {return description;}
}
