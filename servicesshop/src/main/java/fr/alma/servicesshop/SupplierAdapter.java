package fr.alma.servicesshop;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.AxisFault;

import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.ProductCartBean;
import fr.alma.servicesshop.supplier.SupplierProductNotFoundExceptionException;
import fr.alma.servicesshop.supplier.SupplierProductQuantityExceptionException;
import fr.alma.servicesshop.supplier.SupplierStub;
import fr.alma.servicesshop.supplier.SupplierStub.CommandProduct;
import fr.alma.servicesshop.supplier.SupplierStub.CommandProductResponse;
import fr.alma.servicesshop.supplier.SupplierStub.GetProducts;
import fr.alma.servicesshop.supplier.SupplierStub.ProductBean;
import fr.alma.servicesshop.supplier.SupplierStub.SupplierProductBean;

public class SupplierAdapter {

    private static final String SERVICES_SUPPLIER = "http://localhost:9763/services/Supplier/";
    private Map<Long, SupplierProductBean> products;

    public SupplierAdapter() {
        products = new HashMap<>();
    }

    public Collection<SupplierProductBean> getProducts()
            throws AxisFault, RemoteException {
        updateProducts();

        return products.values();
    }

    public SupplierProductBean getProduct(Long productId)
            throws RemoteException {
        if (products.isEmpty()) {
            updateProducts();
        }

        return products.get(productId);
    }

    public boolean commandOneProduct(ProductCartBean oneProduct)
            throws AxisFault, RemoteException,
            SupplierProductQuantityExceptionException,
            SupplierProductNotFoundExceptionException {

        SupplierStub stub = new SupplierStub(SERVICES_SUPPLIER);
        SupplierStub.CommandProduct command = new CommandProduct();
        command.setId(oneProduct.getProductId());
        command.setQuantity(oneProduct.getQuantity());

        CommandProductResponse response = stub.commandProduct(command);
        products.get(oneProduct.getProductId())
                .setQuantity(response.get_return());

        return true;
    }

    private void updateProducts() throws RemoteException {
        products = new HashMap<>();

        SupplierStub stub = new SupplierStub(SERVICES_SUPPLIER);
        SupplierStub.GetProducts prod = new GetProducts();
        SupplierStub.GetProductsResponse prods = stub.getProducts(prod);

        for (SupplierProductBean product : prods.get_return()) {
            products.put(product.getID(), product);
        }
    }


}
