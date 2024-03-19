package com.agrotech.api.model;

public class Property {
    private String product;
    private int land;

    public Property() {
    }

    public Property(String product, int land) {
        this.product = product;
        this.land = land;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getLand() {
        return land;
    }

    public void setLand(int land) {
        this.land = land;
    }
    private String farmer;

}
