package fr.alma.servicesshop;

import java.rmi.RemoteException;

import fr.alma.servicesshop.paymentservice.PaymentServiceStub;
import fr.alma.servicesshop.paymentservice.PaymentServiceStub.GetMoney;
import fr.alma.servicesshop.paymentservice.PaymentServiceStub.Pay;

public class PaymentAdapter {

    private static final String SERVICES_PAYMENT_SERVICES = "http://localhost:9763/services/PaymentService/";

    public double getMoney() throws RemoteException {

        PaymentServiceStub stub = new PaymentServiceStub(
                SERVICES_PAYMENT_SERVICES);

        PaymentServiceStub.GetMoney getMoney = new GetMoney();

        return stub.getMoney(getMoney).get_return();

    }

    public boolean pay(double price) throws RemoteException {

        PaymentServiceStub stub = new PaymentServiceStub(
                SERVICES_PAYMENT_SERVICES);

        PaymentServiceStub.Pay pay = new Pay();
        pay.setPrice(price);

        return stub.pay(pay).get_return();
    }
}
