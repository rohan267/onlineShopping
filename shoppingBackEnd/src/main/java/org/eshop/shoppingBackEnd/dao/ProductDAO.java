package org.eshop.shoppingBackEnd.dao;

import java.util.List;

import org.eshop.shoppingBackEnd.dto.Category;
import org.eshop.shoppingBackEnd.dto.Product;

public interface ProductDAO {

	List<Product> list();
	
	Product get(int id);
	
	boolean add(Product product);
	
	boolean update(Product product);
	
	boolean delete(Product product);
	
	List<Product> listActiveProducts();
	
	List<Product> listActiveProductsByCategory(int categoryId);
	
	List<Product> getLatestActiveProducts(int count);
}
