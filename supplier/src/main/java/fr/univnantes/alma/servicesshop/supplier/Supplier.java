package fr.univnantes.alma.servicesshop.supplier;

import java.util.HashMap;
import java.util.Map;

import fr.univnantes.alma.servicesshop.supplier.ProductBean.CURRENCY;
import fr.univnantes.alma.servicesshop.supplier.exception.ProductNotFoundException;
import fr.univnantes.alma.servicesshop.supplier.exception.ProductQuantityException;

/**
 * Hello world!
 *
 */
public class Supplier {
    private Map<ProductBean, Integer> allProduct;

    public Supplier() {
        allProduct = new HashMap<ProductBean, Integer>();

        allProduct.put(new ProductBean("Product1", 0.75, CURRENCY.EUR), 150);
        allProduct.put(new ProductBean("Product2", 1.00, CURRENCY.EUR), 50);
        allProduct.put(new ProductBean("Product2", 10.50, CURRENCY.EUR), 20);

    }

    public Map<ProductBean, Integer> getProducts() {
        return allProduct;
    }

    public int commandProduct(ProductBean product, int quantity)
            throws ProductNotFoundException, ProductQuantityException {

        int newQuantity;

        try {
            Integer stockedProduct = allProduct.get(product);
            newQuantity = stockedProduct - quantity;
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }

        if (newQuantity < 0)
            throw new ProductQuantityException();

        allProduct.put(product, newQuantity);
        return newQuantity;
    }

}
