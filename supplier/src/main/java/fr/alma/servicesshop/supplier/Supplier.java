package fr.alma.servicesshop.supplier;

import java.util.ArrayList;
import java.util.List;

import fr.alma.servicesshop.supplier.exception.ProductNotFoundException;
import fr.alma.servicesshop.supplier.exception.ProductQuantityException;

/**
 * Hello world!
 *
 */
public class Supplier {
    private static List<SupplierProductBean> allProduct;

    public Supplier() {
        if (allProduct == null) {
            allProduct = new ArrayList<>();

            allProduct.add(new SupplierProductBean(617824022052705280l,
                    new ProductBean("Product1", 0.75, "EUR"), 150));
            allProduct.add(new SupplierProductBean(8308053963714291712l,
                    new ProductBean("Product2", 1.00, "EUR"), 50));
            allProduct.add(new SupplierProductBean(100874887386476544l,
                    new ProductBean("Product3", 10.50, "EUR"), 20));
            allProduct.add(new SupplierProductBean(9051030716826537984l,
                    new ProductBean("Product4", 8.90, "EUR"), 30));
            allProduct.add(new SupplierProductBean(8092228832926427136l,
                    new ProductBean("Product5", 1.50, "EUR"), 50));
            allProduct.add(new SupplierProductBean(8322827431368327168l,
                    new ProductBean("Product6", 15.80, "EUR"), 20));
            allProduct.add(new SupplierProductBean(3863169181625234432l,
                    new ProductBean("Product7", 0.35, "EUR"), 200));

            System.out.println("Setup Supplier BDD : " + allProduct);
        }

    }

    public List<SupplierProductBean> getProducts() {
        return allProduct;
    }

    public int commandProduct(Long id, int quantity)
            throws ProductNotFoundException, ProductQuantityException {

        int newQuantity;
        int productIndex = allProduct
                .indexOf(new SupplierProductBean(id, null, 0));
        SupplierProductBean stockedProduct;

        try {
            stockedProduct = allProduct.get(productIndex);
            newQuantity = stockedProduct.getQuantity() - quantity;
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }

        if (newQuantity < 0)
            throw new ProductQuantityException();

        stockedProduct.setQuantity(newQuantity);
        allProduct.set(productIndex, stockedProduct);
        return newQuantity;
    }

}
