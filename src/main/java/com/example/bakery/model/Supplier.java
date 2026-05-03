package com.example.bakery.model;


public abstract class Supplier {

    private int id;
    private String name;
    private String contactEmail;
    private String phone;
    private String address;

    public Supplier() {}

    public Supplier(int id, String name, String contactEmail, String phone, String address) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.address = address;
    }

    public abstract String getSupplierType();
    public abstract double getDiscountRate();

    @Override
    public String toString() {
        return id + "," + name + "," + contactEmail + "," + phone + "," + address + "," + getSupplierType();
    }

    public static Supplier fromLine(String line) {
        String[] p = line.split(",", -1);
        if (p.length < 6) return null;
        String type = p[5].trim();
        if ("LOCAL".equals(type)) {
            return LocalSupplier.fromLine(line);
        } else {
            return cc.fromLine(line);
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address)
    { this.address = address;
    }
}
