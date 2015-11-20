package fr.alma.servicesshop.ShoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    Map<Integer, ShoppingCartBean> clientCart;

    public ShoppingCart() {
        clientCart = new HashMap<Integer, ShoppingCartBean>();
    }

    public List<ProductCartBean> getShopingCart(Integer client) {
        ShoppingCartBean cart = clientCart.get(client);

        return (cart != null) ? cart.getAllProducts()
                : new ShoppingCartBean().getAllProducts();
    }

    public int add(Integer client, Long productId, int quantity) {
        ShoppingCartBean cart = getClient(client);

        try {
            quantity += cart.getProductQuantity(productId);
        } catch (NullPointerException e) {
        }

        cart.addProduct(productId, quantity);
        clientCart.put(client, cart);

        return cart.getProductQuantity(productId);
    }

    public int remove(Integer client, Long productId, int quantity) {
        ShoppingCartBean cart = getClient(client);

        int productQuantity = cart.getProductQuantity(productId);

        productQuantity -= quantity;

        if (productQuantity <= 0) {
            cart.remove(productId);
            productQuantity = 0;
        } else {
            cart.addProduct(productId, productQuantity);
        }
        return productQuantity;
    }

    private ShoppingCartBean getClient(Integer client) {
        return (clientCart.containsKey(client)) ? clientCart.get(client)
                : new ShoppingCartBean();
    }

}
