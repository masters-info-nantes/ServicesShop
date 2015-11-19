package fr.alma.servicesshop.ShoppingCart;

public class ProductCartBean {

    private Long productId;
    private int quantity;

    public ProductCartBean(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProductCartBean)
            return productId.equals(((ProductCartBean) obj).getProductId());

        return false;
    }

    @Override
    public String toString() {
        return "{" + productId + ":" + quantity + "}";
    }

}
