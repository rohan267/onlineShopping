package org.eshop.shoppingBackEnd.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.eshop.shoppingBackEnd.dao.CategoryDAO;
import org.eshop.shoppingBackEnd.dto.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	static {
		Category category1 = new Category();
		category1.setId(1);
		category1.setName("Television");
		category1.setDescription("Television Description");;
		category1.setImage_url("CAT_1.png");
		category1.setActive(true);
		categories.add(category1);
		
		Category category2 = new Category();
		category2.setId(2);
		category2.setName("Phones");
		category2.setDescription("Phones Description");;
		category2.setImage_url("CAT_2.png");
		category2.setActive(true);
		categories.add(category2);
		
		Category category3 = new Category();
		category3.setId(3);
		category3.setName("Laptop");
		category3.setDescription("Laptop Description");;
		category3.setImage_url("CAT_3.png");
		category3.setActive(true);
		categories.add(category3);
		
	}
	
	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {
		for(Category category: categories) {
			if(category.getId() == id) {
				return category;
			}
		}
		return null;
	}

}
