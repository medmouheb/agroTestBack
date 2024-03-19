package com.agrotech.api.model;

public class DevisProduct {

    public DevisProduct() {
    }

    public DevisProduct(Produit produit, int qte, Tax tax) {
        this.produit = produit;
        this.qte = qte;
        this.tax = tax;
    }

    private Produit produit;
    private int qte;
    private Tax tax;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "DevisProduct{" +
                "produit=" + produit.toString() +
                ", qte=" + qte +
                ", tax=" + tax.toString() +
                '}';
    }
}
