package fr.univnantes.alma.servicesshop.ShoppingCart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.alma.servicesshop.ShoppingCart.ProductBean.CURRENCY;

public class ShoppingCartTest {
    private ProductBean product1 = new ProductBean("Product1", 0.75,
            CURRENCY.EUR);
    private ProductBean product2 = new ProductBean("Product2", 1.00,
            CURRENCY.EUR);

    private ShoppingCartBean myShoppingCart;
    private ShoppingCart shopping;

    private Integer client;

    @Before
    public void setUp() {
        client = 1;
        myShoppingCart = new ShoppingCartBean();
        shopping = new ShoppingCart();
    }

    @Test
    public void testEmptyCart() {
        Assert.assertEquals("ShoppingCart begin empty", myShoppingCart,
                shopping.getShopingCart(client));
    }

    @Test
    public void testAddItem() {
        shopping.add(client, product1, 5);

        myShoppingCart.addProduct(product1, 5);

        Assert.assertEquals("ShoppingCart add product", myShoppingCart,
                shopping.getShopingCart(client));
    }

    @Test
    public void testDoubleAddItem() {
        shopping.add(client, product1, 5);
        shopping.add(client, product1, 3);

        myShoppingCart.addProduct(product1, 8);

        Assert.assertEquals("ShoppingCart add product", myShoppingCart,
                shopping.getShopingCart(client));
    }

    @Test
    public void testDoubleItem() {
        myShoppingCart.addProduct(product1, 5);
        myShoppingCart.addProduct(product2, 8);
        shopping.add(client, product1, 1);
        shopping.add(client, product2, 1);
        shopping.add(client, product2, 7);
        shopping.add(client, product1, 4);

        Assert.assertEquals("ShoppingCart add all product", myShoppingCart,
                shopping.getShopingCart(client));
    }

    @Test
    public void testDeleteItem() {
        shopping.add(client, product1, 1);
        shopping.remove(client, product1, 1);

        Assert.assertEquals("ShoppingCart remove product", myShoppingCart,
                shopping.getShopingCart(client));

    }

    @Test
    public void testComplexDeleteItem() {
        myShoppingCart.addProduct(product2, 2);
        shopping.add(client, product1, 1);
        shopping.add(client, product2, 1);
        shopping.add(client, product2, 4);
        shopping.remove(client, product2, 3);
        shopping.remove(client, product1, 4);

        Assert.assertEquals("ShoppingCart remove product", myShoppingCart,
                shopping.getShopingCart(client));

    }
}
