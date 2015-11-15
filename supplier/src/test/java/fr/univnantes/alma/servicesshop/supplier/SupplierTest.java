package fr.univnantes.alma.servicesshop.supplier;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.univnantes.alma.servicesshop.supplier.ProductBean.CURRENCY;
import fr.univnantes.alma.servicesshop.supplier.exception.ProductNotFoundException;
import fr.univnantes.alma.servicesshop.supplier.exception.ProductQuantityException;

public class SupplierTest {

    private Supplier supplier;
    private Map<ProductBean, Integer> listProduct;
    private ProductBean product1 = new ProductBean("Product1", 0.75,
            CURRENCY.EUR);
    private ProductBean product2 = new ProductBean("Product2", 1.00,
            CURRENCY.EUR);

    @Before
    public void setUp() throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        supplier = new Supplier();

        listProduct = new HashMap<ProductBean, Integer>();

        listProduct.put(product1, 150);
        listProduct.put(product2, 50);

        Field field = Supplier.class.getDeclaredField("allProduct");
        field.setAccessible(true);
        field.set(supplier, new HashMap<ProductBean, Integer>(listProduct));
    }

    @Test
    public void testGetProducts() {
        assertEquals("List Product is correct", listProduct,
                supplier.getProducts());
    }

    @Test
    public void testCommandProduct() {

        int quantityCommanded = 50;
        int newQuantity = listProduct.get(product1) - quantityCommanded;
        listProduct.put(product1, newQuantity);
        try {
            assertEquals("New Stocked product is correct", newQuantity,
                    supplier.commandProduct(product1, quantityCommanded));
        } catch (ProductNotFoundException | ProductQuantityException e) {
            e.printStackTrace();
        }
        assertEquals("New Stocked product is Correct on all Product",
                listProduct, supplier.getProducts());
    }

    @Test(expected = ProductNotFoundException.class)
    public void testBadCommandProduct()
            throws ProductNotFoundException, ProductQuantityException {

        supplier.commandProduct(
                new ProductBean("productNotExisting", 3.00, CURRENCY.EUR), 1);
    }

    @Test(expected = ProductQuantityException.class)
    public void testBadQuantityCommandProduct()
            throws ProductNotFoundException, ProductQuantityException {
        supplier.commandProduct(product2, Integer.MAX_VALUE);
    }
}
