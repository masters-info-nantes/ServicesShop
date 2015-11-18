package fr.univnantes.alma.servicesshop.paymentService;

/**
 * payment service
 *
 */
public class paymentService
{
    
	private double money=1000.0;
	
	public double getMoney() {
		return money;
	}
	
	
	public boolean pay(double price) {
		if(price-money >= 0){
			money = price - money;
			return true;
		}
		return false;
	}
	
}
