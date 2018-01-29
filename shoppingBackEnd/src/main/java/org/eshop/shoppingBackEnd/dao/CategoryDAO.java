package org.eshop.shoppingBackEnd.dao;

import java.util.List;

import org.eshop.shoppingBackEnd.dto.Category;

public interface CategoryDAO {

	List<Category> list();
	
	Category get(int id);
}
