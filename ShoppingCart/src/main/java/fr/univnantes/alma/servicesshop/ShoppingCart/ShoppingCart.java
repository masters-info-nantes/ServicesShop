package fr.univnantes.alma.servicesshop.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    Map<Integer, ShoppingCartBean> clientCart;

    public ShoppingCart() {
        clientCart = new HashMap<Integer, ShoppingCartBean>();
    }

    public ShoppingCartBean getShopingCart(Integer client) {
        ShoppingCartBean cart = clientCart.get(client);

        return (cart != null) ? cart : new ShoppingCartBean();
    }

    public void add(Integer client, ProductBean product, int quantity) {
        ShoppingCartBean cart = getClient(client);

        try {
            quantity += cart.getProductQuantity(product);
        } catch (NullPointerException e) {
        }

        cart.addProduct(product, quantity);
        clientCart.put(client, cart);
    }

    public void remove(Integer client, ProductBean product, int quantity) {
        ShoppingCartBean cart = getClient(client);

        int productQuantity = cart.getProductQuantity(product);

        productQuantity -= quantity;

        if (productQuantity <= 0) {
            cart.remove(product);
        } else {
            cart.addProduct(product, productQuantity);
        }
    }

    private ShoppingCartBean getClient(Integer client) {
        return (clientCart.containsKey(client)) ? clientCart.get(client)
                : new ShoppingCartBean();
    }

}
