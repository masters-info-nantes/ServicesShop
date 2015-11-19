package fr.alma.servicesshop;


public class ProductBean {

    enum CURRENCY {
        EUR, USD, GBP
    }

    private String name;
    private double price;
    private CURRENCY currency;

    public ProductBean(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(CURRENCY currency) {
        this.currency = currency;
    }

}
