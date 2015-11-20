package fr.alma.servicesshop;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.AxisFault;

import fr.alma.servicesshop.shoppingcart.ShoppingCartStub;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Add;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.GetShopingCart;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.GetShopingCartResponse;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.ProductCartBean;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Remove;
import fr.alma.servicesshop.supplier.SupplierProductNotFoundExceptionException;
import fr.alma.servicesshop.supplier.SupplierProductQuantityExceptionException;
import fr.alma.servicesshop.supplier.SupplierStub;
import fr.alma.servicesshop.supplier.SupplierStub.GetProducts;
import fr.alma.servicesshop.supplier.SupplierStub.ProductBean;
import fr.alma.servicesshop.supplier.SupplierStub.SupplierProductBean;

/**
 * shop
 */
public class ServiceShop {

    public static void main(String[] args) throws AxisFault, RemoteException,
            SupplierProductQuantityExceptionException,
            SupplierProductNotFoundExceptionException {
        ServiceShop shop = new ServiceShop();

        SupplierProductBean product = shop.getProducts()[0];
        System.out.println(product.getProduct().getName());

        shop.add(1, product.getID(), 10);

        // récupération du shopping cart
        ShoppingCartStub shoppingStub = new ShoppingCartStub(
                "http://localhost:9763/services/ShoppingCart/");
        ShoppingCartStub.GetShopingCart cart = new GetShopingCart();
        cart.setClient(1);
        GetShopingCartResponse res = shoppingStub.getShopingCart(cart);
        ProductCartBean[] shoppingCart = res.get_return();

    }

    private Map<Long, ProductBean> products;

    public int add(int client, long idProduct, int quantity)
            throws AxisFault, RemoteException {

        ShoppingCartStub shoppingStub = new ShoppingCartStub();

        ShoppingCartStub.Add addingProd = new Add();
        addingProd.setClient(client);
        addingProd.setProductId(idProduct);
        addingProd.setQuantity(quantity);

        ShoppingCartStub.AddResponse res = shoppingStub.add(addingProd);

        return res.get_return();
    }

    public int remove(Integer client, Long productId, int quantity)
            throws RemoteException {

        ShoppingCartStub shoppingStub = new ShoppingCartStub();

        ShoppingCartStub.Remove removingProd = new Remove();
        removingProd.setClient(client);
        removingProd.setProductId(productId);
        removingProd.setQuantity(quantity);

        ShoppingCartStub.RemoveResponse res = shoppingStub.remove(removingProd);

        return res.get_return();
    }

    public SupplierProductBean[] getProducts()
            throws AxisFault, RemoteException {

        products = new HashMap<Long, SupplierStub.ProductBean>();

        SupplierStub stub = new SupplierStub(
                "http://localhost:9763/services/Supplier/");
        SupplierStub.GetProducts prod = new GetProducts();
        SupplierStub.GetProductsResponse prods = stub.getProducts(prod);

        for (SupplierProductBean product : prods.get_return()) {
            products.put(product.getID(), product.getProduct());
        }

        return prods.get_return();
    }

    public Boolean commandProduct(int Client) throws AxisFault, RemoteException,
            SupplierProductQuantityExceptionException,
            SupplierProductNotFoundExceptionException {

        // récupération du shopping cart
        ShoppingCartStub shoppingStub = new ShoppingCartStub(
                "http://localhost:9763/services/ShoppingCart/");
        ShoppingCartStub.GetShopingCart cart = new GetShopingCart();
        cart.setClient(Client);
        GetShopingCartResponse res = shoppingStub.getShopingCart(cart);
        ProductCartBean[] shoppingCart = res.get_return();

        double price = 0.0;
        return true;
        /*
         * for (SupplierProductBean product : (ArrayList<SupplierProductBean>)
         * shoppingCart .getAllProducts()) {
         * 
         * // passage d'une commande au prêt du supplier SupplierStub
         * supplierStub = new SupplierStub(
         * "http://localhost:9763/services/Supplier/");
         * SupplierStub.CommandProduct purchassing = new CommandProduct();
         * purchassing.setId(product.getID());
         * purchassing.setQuantity(product.getQuantity());
         * CommandProductResponse response = supplierStub
         * .commandProduct(purchassing);
         * 
         * // récupération du prix à payer price += product.getQuantity()
         * products.get(product.getID()).getPrice(); }
         * 
         * PaymentServiceStub stubPayment = new PaymentServiceStub();
         * PaymentServiceStub.Pay payment = new Pay(); payment.setPrice(price);
         * 
         * PayResponse paymentRes = stubPayment.pay(payment);
         * 
         * return paymentRes.get_return();
         */
    }

}
