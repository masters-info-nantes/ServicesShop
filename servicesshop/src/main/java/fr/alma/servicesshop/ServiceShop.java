package fr.alma.servicesshop;

import org.apache.axis2.AxisFault;


import fr.alma.servicesshop.paymentservice.PaymentServiceStub;
import fr.alma.servicesshop.paymentservice.PaymentServiceStub.Pay;
import fr.alma.servicesshop.paymentservice.PaymentServiceStub.PayResponse;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Add;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.GetShopingCart;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.GetShopingCartResponse;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.Remove;
import fr.alma.servicesshop.shoppingcart.ShoppingCartStub.ShoppingCartBean;
import fr.alma.servicesshop.supplier.SupplierProductNotFoundExceptionException;
import fr.alma.servicesshop.supplier.SupplierProductQuantityExceptionException;
import fr.alma.servicesshop.supplier.SupplierStub;
import fr.alma.servicesshop.supplier.SupplierStub.CommandProduct;
import fr.alma.servicesshop.supplier.SupplierStub.CommandProductResponse;
import fr.alma.servicesshop.supplier.SupplierStub.GetProducts;
import fr.alma.servicesshop.supplier.SupplierStub.ProductBean;
import fr.alma.servicesshop.supplier.SupplierStub.SupplierProductBean;



import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * shop 
 */
public class ServiceShop
{
	
//	public static void main (String [] args) throws RemoteException, SupplierProductQuantityExceptionException, SupplierProductNotFoundExceptionException{
//				
//		HashMap<Long, SupplierStub.ProductBean> products = new HashMap<Long, SupplierStub.ProductBean>();
//		
//		SupplierStub stub = new SupplierStub("http://localhost:9763/services/Supplier/");
//		SupplierStub.GetProducts prod = new  GetProducts();
//		SupplierStub.GetProductsResponse prods = stub.getProducts(prod);
//		
//		for (SupplierProductBean product : prods.get_return()) {
//			products.put(product.getID(), product.getProduct());
//		}
//
////		// récupération du shopping cart
////				ShoppingCartStub shoppingStub = new ShoppingCartStub("http://localhost:9763/services/ShoppingCart/");
////				ShoppingCartStub.GetShopingCart cart= new GetShopingCart();
////				cart.setClient(1);
////				ShoppingCartStub.GetShopingCartResponse res = shoppingStub.getShopingCart(cart);
////				ShoppingCartBean shoppingCart = res.get_return();
////				
//				double price=0.0;
////			
////				for (SupplierProductBean product: (ArrayList<SupplierProductBean>)shoppingCart.getAllProducts()) {
////					
////					// passage d'une commande au prêt du supplier
////					SupplierStub supplierStub = new SupplierStub();
////					SupplierStub.CommandProduct purchassing = new  CommandProduct();
////					purchassing.setId(product.getID());
////					purchassing.setQuantity(product.getQuantity());			
////					CommandProductResponse response = supplierStub.commandProduct(purchassing);
////					
////					//récupération du prix à payer
////					price += product.getQuantity() * products.get(product.getID()).getPrice();
////				}
//				
//				PaymentServiceStub stubPayment = new PaymentServiceStub("http://localhost:9763/services/paymentService/");
//				PaymentServiceStub.Pay payment = new Pay();
//				payment.setPrice(price);
//				
//				PayResponse paymentRes  = stubPayment.pay(payment);
//				
//				//return paymentRes.get_return();
//		
//	}
	
	
	private Map<Long,ProductBean> products;
	
	
	
	public int add(int client,long idProduct,int quantity) throws AxisFault, RemoteException {
		
		ShoppingCartStub shoppingStub = new ShoppingCartStub();
		
		ShoppingCartStub.Add addingProd= new Add();
		addingProd.setClient(client);
		addingProd.setProductId(idProduct);
		addingProd.setQuantity(quantity);
		
		ShoppingCartStub.AddResponse res = shoppingStub.add(addingProd);
		
		return res.get_return();
	}
	
	public int remove(Integer client, Long productId, int quantity) throws RemoteException {
		
		ShoppingCartStub shoppingStub = new ShoppingCartStub();
		
		ShoppingCartStub.Remove removingProd= new Remove();
		removingProd.setClient(client);
		removingProd.setProductId(productId);
		removingProd.setQuantity(quantity);
		
		ShoppingCartStub.RemoveResponse res = shoppingStub.remove(removingProd);
		
		return res.get_return();
	}
	
	public SupplierProductBean[] getProducts() throws AxisFault, RemoteException {
		
		products = new HashMap<Long, SupplierStub.ProductBean>();
		
		SupplierStub stub = new SupplierStub("http://localhost:9763/services/Supplier/");
		SupplierStub.GetProducts prod = new  GetProducts();
		SupplierStub.GetProductsResponse prods = stub.getProducts(prod);
		
		for (SupplierProductBean product : prods.get_return()) {
			products.put(product.getID(), product.getProduct());
		}
		
		return prods.get_return();
	}
	

	public Boolean commandProduct(int Client) throws AxisFault, RemoteException, SupplierProductQuantityExceptionException, SupplierProductNotFoundExceptionException {
		
		// récupération du shopping cart
		ShoppingCartStub shoppingStub = new ShoppingCartStub("http://localhost:9763/services/ShoppingCart/");
		ShoppingCartStub.GetShopingCart cart= new GetShopingCart();
		cart.setClient(Client);
		GetShopingCartResponse res = shoppingStub.getShopingCart(cart);
		ShoppingCartBean shoppingCart = res.get_return();
		
		double price=0.0;
	
		for (SupplierProductBean product: (ArrayList<SupplierProductBean>)shoppingCart.getAllProducts()) {
			
			// passage d'une commande au prêt du supplier
			SupplierStub supplierStub = new SupplierStub("http://localhost:9763/services/Supplier/");
			SupplierStub.CommandProduct purchassing = new  CommandProduct();
			purchassing.setId(product.getID());
			purchassing.setQuantity(product.getQuantity());			
			CommandProductResponse response = supplierStub.commandProduct(purchassing);
			
			//récupération du prix à payer
			price += product.getQuantity() * products.get(product.getID()).getPrice();
		}
		
		PaymentServiceStub stubPayment = new PaymentServiceStub();
		PaymentServiceStub.Pay payment = new Pay();
		payment.setPrice(price);
		
		PayResponse paymentRes  = stubPayment.pay(payment);
		
		return paymentRes.get_return();
		
	}
	
}
