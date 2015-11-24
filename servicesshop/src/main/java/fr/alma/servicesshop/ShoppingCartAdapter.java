package fr.alma.servicesshop;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.axis2.AxisFault;

import fr.alma.servicesshop.shoppingcart.ShoppingCartStub;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Add;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.GetShopingCart;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.ProductCartBean;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Remove;

public class ShoppingCartAdapter {

    private static final String SERVICES_SHOPPING_CART = "http://localhost:9763/services/ShoppingCart/";

    public List<ProductCartBean> getProduct(int client) throws RemoteException {

        ShoppingCartStub stub = new ShoppingCartStub(SERVICES_SHOPPING_CART);
        ShoppingCartStub.GetShopingCart shopingCart = new GetShopingCart();
        shopingCart.setClient(client);

        ShoppingCartStub.GetShopingCartResponse myShopingCart = stub
                .getShopingCart(shopingCart);

        List<ProductCartBean> productList = new ArrayList<>();
        Collections.addAll(productList, myShopingCart.get_return());
        return productList;

    }

    public int add(int client, long idProduct, int quantity)
            throws AxisFault, RemoteException {

        ShoppingCartStub shoppingStub = new ShoppingCartStub(
                SERVICES_SHOPPING_CART);

        ShoppingCartStub.Add addingProd = new Add();
        addingProd.setClient(client);
        addingProd.setProductId(idProduct);
        addingProd.setQuantity(quantity);

        ShoppingCartStub.AddResponse res = shoppingStub.add(addingProd);

        return res.get_return();
    }

    public int remove(Integer client, Long productId, int quantity)
            throws RemoteException {

        ShoppingCartStub shoppingStub = new ShoppingCartStub(
                SERVICES_SHOPPING_CART);

        ShoppingCartStub.Remove removingProd = new Remove();
        removingProd.setClient(client);
        removingProd.setProductId(productId);
        removingProd.setQuantity(quantity);

        ShoppingCartStub.RemoveResponse res = shoppingStub.remove(removingProd);

        return res.get_return();
    }

}
