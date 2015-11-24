package fr.alma.servicesshop;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.ProductCartBean;
import fr.alma.servicesshop.supplier.SupplierProductNotFoundExceptionException;
import fr.alma.servicesshop.supplier.SupplierProductQuantityExceptionException;
import fr.alma.servicesshop.supplier.SupplierStub.ProductBean;
import fr.alma.servicesshop.supplier.SupplierStub.SupplierProductBean;

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

    public List<SupplierProductBean> getProducts()
            throws AxisFault, RemoteException {

        return new ArrayList<>(supplier.getProducts());
    }

    public Boolean commandProduct(int client) throws AxisFault, RemoteException,
            SupplierProductQuantityExceptionException,
            SupplierProductNotFoundExceptionException {

        boolean success = true;

        List<ProductCartBean> myProducts = shoppingCart.getProduct(client);

        double price = 0.0;
        for (ProductCartBean oneProduct : myProducts) {
            ProductBean supplierProduct = supplier
                    .getProduct(oneProduct.getProductId()).getProduct();
            price += supplierProduct.getPrice();
        }

        success = payment.pay(price);

        if (success) {
            supplier.commandProducts(myProducts);
        }

        return success;
    }

}
