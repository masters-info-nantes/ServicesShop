package fr.univnantes.alma.servicesshop.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartBean {
    private Map<ProductBean, Integer> allProduct;

    public ShoppingCartBean() {
        allProduct = new HashMap<ProductBean, Integer>();
    }

    public void addProduct(ProductBean product, int quantity) {
        allProduct.put(product, quantity);
    }

    public void remove(ProductBean product) {
        allProduct.remove(product);
    }

    public Integer getProductQuantity(ProductBean product) {
        return allProduct.get(product);
    }

    public Map<ProductBean, Integer> getAllProducts() {
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
