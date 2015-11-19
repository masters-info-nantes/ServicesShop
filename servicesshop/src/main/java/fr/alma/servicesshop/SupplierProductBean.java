package fr.alma.servicesshop;

public class SupplierProductBean {
    private Long ID;
    private ProductBean product;
    private int quantity;

    public SupplierProductBean(Long id, ProductBean product, int quantity) {
        ID = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SupplierProductBean)
            return ID.equals(((SupplierProductBean) obj).getID());

        return false;
    }

    @Override
    public String toString() {
        return "{" + ID + ":" + product.toString() + "=" + quantity + "}";
    }

}
