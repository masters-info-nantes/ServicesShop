package fr.alma.servicesshop.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartBean {

    private List<ProductCartBean> allProduct;

    public ShoppingCartBean() {
        allProduct = new ArrayList<ProductCartBean>();
    }

    public void addProduct(Long productId, int quantity) {
        ProductCartBean product = new ProductCartBean(productId, quantity);
        if (allProduct.contains(product)) {
            allProduct.set(allProduct.indexOf(product), product);
        } else {
            allProduct.add(product);
        }
    }

    public void remove(Long productId) {
        allProduct.remove(new ProductCartBean(productId, 0));
    }

    public Integer getProductQuantity(Long productID) {
        for (ProductCartBean cart : allProduct) {
            if (productID.equals(cart.getProductId()))
                return cart.getQuantity();
        }
        return 0;
    }

    public List<ProductCartBean> getAllProducts() {
        return allProduct;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShoppingCartBean)
            return allProduct.equals(((ShoppingCartBean) obj).getAllProducts());

        return false;
    }

    @Override
    public String toString() {
        return allProduct.toString();
    }

}
