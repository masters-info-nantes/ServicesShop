package fr.alma.servicesshop.paymentService;

/**
 * payment service
 *
 */
public class PaymentService
{
    
	private double money=1000.0;
	
	public double getMoney() {
		return money;
	}
	
	
	public boolean pay(double price) {
		
		if(money-price >= 0){
			money =  money - price ;
			return true;
		}
		return false;
	}
	
}
