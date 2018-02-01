package org.eshop.shoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.eshop.shoppingBackEnd.dao.ProductDAO;
import org.eshop.shoppingBackEnd.dto.Product;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductTestCase {

private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.eshop.shoppingBackEnd");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	@Test
	@Ignore
	public void testAddProduct() {
		product = new Product();
		//product.setId(6);
		product.setCode("PRDLPTP001");
		product.setName("Macbook Pro 15");
		product.setBrand("Apple");
		product.setDescription("2016 Macbook Pro i7, 3.0GHz, 16GB RAM, 1TB SSD, Space Gray");
		product.setPrice(1350.00);
		product.setQuantity(500);
		product.setActive(true);
		product.setCategoryId(1);
		product.setSupplierId(1);
		product.setPurchases(400);
		product.setViews(2000);
		
		assertEquals("Product added Successfully", true, productDAO.add(product));
	}
	
	@Test
	@Ignore
	public void testListActiveProducts() {
		assertEquals("List of active products failed", 6, productDAO.listActiveProducts().size());
	}
	
	@Test
	@Ignore
	public void testListActiveProductsByCategory() {
		assertEquals("List of active products failed", 3, productDAO.listActiveProductsByCategory(1).size());
		
		assertEquals("List of active products failed", 0, productDAO.listActiveProductsByCategory(2).size());

	}
	
	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("List of active products failed", 3, productDAO.getLatestActiveProducts(3).size());
	}
	
}
