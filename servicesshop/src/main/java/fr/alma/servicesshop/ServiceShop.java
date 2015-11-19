package fr.alma.servicesshop;

import org.apache.axis2.AxisFault;


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
				
	public SupplierProductBean[] getProducts() throws AxisFault, RemoteException{
	
		SupplierStub stub = new SupplierStub();
		
		SupplierStub.GetProducts prod = new  GetProducts();
		// prod.setParam pour passer les paramètres. 
		
		SupplierStub.GetProductsResponse products = stub.getProducts(prod);

		return products.get_return();
	}
	
	
	
	
	public Boolean commandProduct(Long id) throws AxisFault, RemoteException {
		
		SupplierStub stub = new SupplierStub();
		
		SupplierStub.CommandProduct prod = new  GetProducts();
		// prod.setParam pour passer les paramètres. 
		
		SupplierStub.CommandProductResponse products = stub.getProducts(prod);

		return products.get_return();
	}
	
}
