package org.eshop.shoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.eshop.shoppingBackEnd.dao.CategoryDAO;
import org.eshop.shoppingBackEnd.dto.Category;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDao;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.eshop.shoppingBackEnd");
		context.refresh();
		
		categoryDao = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	@Test
	@Ignore
	public void testAddCategory() {
//		category = new Category();
//		category.setId(4);
//		category.setName("Printer");
//		category.setDescription("Computer printers");;
//		category.setImageURL("CAT_4.png");
		
		category = new Category();
		category.setId(4);
		category.setName("Printer");
		category.setDescription("Computer Printers");;
		category.setImageURL("CAT_5.png");
		category.setActive(true);
		
		assertEquals("Successfully added a category inside table", true, categoryDao.add(category));
		
	}
	
	@Test
	@Ignore
	public void testGetCategory() {
		category = categoryDao.get(2);
		assertEquals("Successfully fetched a single category from table", "Television", category.getName());
		
	}

	@Test
	@Ignore
	public void testUpdateCategory() {
		category = categoryDao.get(2);
		category.setName("Television");
		assertEquals("Successfully update a single category in table", true, categoryDao.update(category));
		
	}
	
	@Test
	@Ignore
	public void testDeleteCategory() {
		category = categoryDao.get(4);
		//category.setName("Television");
		assertEquals("Successfully deleted a single category in table", true, categoryDao.delete(category));
		
	}
	
	@Test
	@Ignore
	public void testListCategory() {
		category = categoryDao.get(2);
		assertEquals("Successfully fetched the list of Categories from Table", 4, categoryDao.list().size());
	}

	@Test
	public void testCRUDCategory() {
		
		// Add operation
		category = new Category();
		category.setId(1);
		category.setName("Printer");
		category.setDescription("Computer Printers");;
		category.setImageURL("CAT_5.png");
		category.setActive(true);
		assertEquals("Successfully added a category inside table", true, categoryDao.add(category));
		
		category = new Category();
		category.setId(2);
		category.setName("Television");
		category.setDescription("Description of Television category");
		category.setImageURL("CAT_2.png");
		category.setActive(true);
		assertEquals("Successfully added a category inside table", true, categoryDao.add(category));

		
		// Get list
		category = categoryDao.get(2);
		category.setName("Television");
		assertEquals("Successfully update a single category in table", true, categoryDao.update(category));
		
		// Delete
//		category = categoryDao.get(4);
		assertEquals("Successfully deleted a single category in table", true, categoryDao.delete(category));
		
		// list
		assertEquals("Successfully fetched the list of Categories from Table", 1, categoryDao.list().size());

		
	}
}
