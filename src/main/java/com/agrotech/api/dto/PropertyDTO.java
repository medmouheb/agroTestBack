package com.agrotech.api.dto;

public class PropertyDTO {
    private String product;
    private int land;
    private String farmer;

    public PropertyDTO() {
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

}
