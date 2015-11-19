package fr.alma.servicesshop;

import org.apache.axis2.AxisFault;


import fr.alma.servicesshop.paymentservice.PaymentServiceStub;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Add;
import fr.alma.servicesshop.supplier.SupplierStub;
import fr.alma.servicesshop.supplier.SupplierStub.GetProducts;
import fr.alma.servicesshop.supplier.SupplierStub.SupplierProductBean;



import java.rmi.RemoteException;
import java.util.ArrayList;


/**
 * shop 
 */
public class ServiceShop
{

	
	public int add(int client,long idProduct,int quantity) throws AxisFault, RemoteException {
		
		ShoppingCartStub shoppingStub = new ShoppingCartStub();
		
		ShoppingCartStub.Add addingProd= new Add();
		addingProd.setClient(client);
		addingProd.setProductId(idProduct);
		addingProd.setQuantity(quantity);
		
		ShoppingCartStub.AddResponse res = shoppingStub.add(addingProd);
		
		return res.get_return();
	}
	
	public SupplierProductBean[] getProducts() throws AxisFault, RemoteException {
	
		SupplierStub stub = new SupplierStub();
		
		SupplierStub.GetProducts prod = new  GetProducts();
		
		SupplierStub.GetProductsResponse products = stub.getProducts(prod);

		return products.get_return();
	}
	
	
	
	
//	
//	public Boolean commandProduct(Long id) throws AxisFault, RemoteException {
//		
//		ShoppingCartStub shoppingStub = new ShoppingCartStub();
//		
//		ShoppingCartStub.GetShopingCart addingProd= new Add();
//		addingProd.setClient(client);
//		addingProd.setProductId(idProduct);
//		addingProd.setQuantity(quantity);
//		
//		ShoppingCartStub.AddResponse res = shoppingStub.add(addingProd);
//		
//		return res.get_return();
//	}
	
}
