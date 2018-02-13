package org.eshop.shoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.eshop.shoppingBackEnd.dao.CartLineDAO;
import org.eshop.shoppingBackEnd.dao.ProductDAO;
import org.eshop.shoppingBackEnd.dao.UserDAO;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.CartLine;
import org.eshop.shoppingBackEnd.dto.Product;
import org.eshop.shoppingBackEnd.dto.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartLineTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.eshop.shoppingBackEnd");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
	}

	@Test
	public void testAddNewCartLine() {
	
		// get user
		User user = userDAO.getByEmail("rs@gmail.com");
		
		// fetch cart
		Cart cart = user.getCart();
		
		// get product
		product = productDAO.get(1);
		
		// create new cart line
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getPrice());
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(cartLine.getProductCount() * product.getPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add cart line", true, cartLineDAO.add(cartLine));
		
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to update cart", true, cartLineDAO.updateCart(cart));

	}
	
}