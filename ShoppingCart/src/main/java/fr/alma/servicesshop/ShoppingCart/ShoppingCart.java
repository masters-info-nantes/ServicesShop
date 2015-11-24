package fr.alma.servicesshop.ShoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    Map<Integer, ShoppingCartRepository> clientCart;

    public ShoppingCart() {
        clientCart = new HashMap<Integer, ShoppingCartRepository>();
    }

    public List<ProductCartBean> getShopingCart(Integer client) {
        ShoppingCartRepository cart = clientCart.get(client);

        return (cart != null) ? cart.getAllProducts()
                : new ShoppingCartRepository().getAllProducts();
    }

    public int add(Integer client, Long productId, int quantity) {
        ShoppingCartRepository cart = getClient(client);

        try {
            quantity += cart.getProductQuantity(productId);
        } catch (NullPointerException e) {
        }

        cart.addProduct(productId, quantity);
        clientCart.put(client, cart);

        return cart.getProductQuantity(productId);
    }

    public int remove(Integer client, Long productId, int quantity) {
        ShoppingCartRepository cart = getClient(client);

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

    private ShoppingCartRepository getClient(Integer client) {
        return (clientCart.containsKey(client)) ? clientCart.get(client)
                : new ShoppingCartRepository();
    }

}
