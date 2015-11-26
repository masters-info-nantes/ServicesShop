package fr.alma.servicesshop;

import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.ProductCartBean;
import fr.alma.servicesshop.supplier.SupplierProductNotFoundExceptionException;
import fr.alma.servicesshop.supplier.SupplierProductQuantityExceptionException;
import fr.alma.servicesshop.supplier.SupplierStub.ProductBean;
import fr.alma.servicesshop.supplier.SupplierStub.SupplierProductBean;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * shop
 */
public class ServiceShop {

    private SupplierAdapter supplier;
    private ShoppingCartAdapter shoppingCart;
    private PaymentAdapter payment;

    public ServiceShop() {
        supplier = new SupplierAdapter();
        shoppingCart = new ShoppingCartAdapter();
        payment = new PaymentAdapter();
    }

    public int add(int client, long idProduct, int quantity)
            throws AxisFault, RemoteException {

        return shoppingCart.add(client, idProduct, quantity);
    }

    public int remove(Integer client, Long productId, int quantity)
            throws RemoteException {

        return shoppingCart.remove(client, productId, quantity);
    }

    public List<ProductCartBean> getShoppingCart(int client) throws RemoteException {
        return shoppingCart.getProduct(client);
    }

    public List<SupplierProductBean> getProducts()
            throws AxisFault, RemoteException {

        return new ArrayList<>(supplier.getProducts());
    }

    public double commandProduct(int client) throws AxisFault, RemoteException {

        List<ProductCartBean> myProducts = shoppingCart.getProduct(client);

        double price = 0.0;
        for (ProductCartBean oneProduct : myProducts) {

            try {
                supplier.commandOneProduct(oneProduct);

                ProductBean supplierProduct = supplier
                        .getProduct(oneProduct.getProductId()).getProduct();
                price += supplierProduct.getPrice();

            } catch (SupplierProductQuantityExceptionException
                    | SupplierProductNotFoundExceptionException e) {
                e.printStackTrace();
            }

        }

        if (price != 0.0) {
            payment.pay(price);
        }

        return price;
    }
}
