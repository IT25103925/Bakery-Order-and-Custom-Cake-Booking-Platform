package com.example.bakery.model;

public class PerishableProduct extends Product {

    private String expiryDate;   // e.g. "2025-12-31"
    private int shelfLifeDays;

    public PerishableProduct(int id, String name, double price, String category,
                             int stockQuantity, String expiryDate, int shelfLifeDays) {
        super(id, name, price, category, stockQuantity); // calls parent constructor
        this.expiryDate = expiryDate;
        this.shelfLifeDays = shelfLifeDays;
    }

    // Polymorphism: overrides abstract method from Product
    @Override
    public String getDisplayPrice() {
        return String.format("Rs. %.2f (Expires: %s)", getPrice(), expiryDate);
    }

    @Override
    public String getProductType() {
        return "PERISHABLE";
    }

    // Serialize to CSV: id,name,price,category,stock,type,expiryDate,shelfLifeDays
    @Override
    public String toCsvLine() {
        return getId() + "," + getName() + "," + getPrice() + "," + getCategory() + ","
                + getStockQuantity() + ",PERISHABLE," + expiryDate + "," + shelfLifeDays;
    }

    // Parse from CSV line
    public static PerishableProduct fromCsvLine(String line) {
        String[] p = line.split(",");
        return new PerishableProduct(
                Integer.parseInt(p[0].trim()),
                p[1].trim(),
                Double.parseDouble(p[2].trim()),
                p[3].trim(),
                Integer.parseInt(p[4].trim()),
                p[6].trim(),
                Integer.parseInt(p[7].trim())
        );
    }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public int getShelfLifeDays() { return shelfLifeDays; }
    public void setShelfLifeDays(int shelfLifeDays) { this.shelfLifeDays = shelfLifeDays; }
}