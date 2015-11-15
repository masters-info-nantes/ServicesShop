package fr.univnantes.alma.servicesshop.ShoppingCart;

public class ProductBean {

    enum CURRENCY {
        EUR, USD, GBP
    }

    private String name;
    private double price;
    private CURRENCY currency;

    public ProductBean(String name, double price, CURRENCY currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductBean))
            return false;

        ProductBean other = (ProductBean) obj;

        return name.equals(other.getName()) && price == other.getPrice()
                && currency.equals(other.getCurrency());
    }

    @Override
    public String toString() {
        return name + " : " + price + " " + currency;
    }

}
