package org.eshop.shoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.eshop.shoppingBackEnd.dao.UserDAO;
import org.eshop.shoppingBackEnd.dto.Address;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.User;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user;
	private Cart cart;
	private Address address;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.eshop.shoppingBackEnd");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
//	@Test
//	public void testAdd() {
//		user = new User();
////		user.setFirstName("Sachin");
////		user.setLastName("Tendulkar");
////		user.setEmail("saching.t@gmail.com");
////		user.setContactNumber("9109109100");
////		user.setRole("admin");
////		user.setPassword("sachin");
//		
////		user.setFirstName("Kapil");
////		user.setLastName("Dev");
////		user.setEmail("kapil.d@gmail.com");
////		user.setContactNumber("9109109111");
////		user.setRole("user");
////		user.setPassword("kapil");
//		
//		user.setFirstName("Virendra");
//		user.setLastName("Sehwag");
//		user.setEmail("viru.s@gmail.com");
//		user.setContactNumber("9109109112");
//		user.setRole("user");
//		user.setPassword("viru");
//		
//		assertEquals("Failed to add user",true,userDAO.addUser(user));
//		
//		address = new Address();
////		address.setAddressLineOne("bandra 1");
////		address.setAddressLineTwo("bandra 2");
////		address.setBilling(true);
////		address.setCity("Mumbai");
////		address.setState("Maharashtra");
////		address.setPostalCode("4000001");
////		address.setCountry("India");
////		address.setUserId(user.getId());
//		
//		
////		address.setAddressLineOne("chandigardh 1");
////		address.setAddressLineTwo("chandigardh 2");
////		address.setBilling(true);
////		address.setCity("Chandigardh");
////		address.setState("Punjab");
////		address.setPostalCode("6000001");
////		address.setCountry("India");
////		address.setUserId(user.getId());
////		assertEquals("Failed to add address", true,userDAO.addAddres(address));
//		
//		address.setAddressLineOne("delhi 1");
//		address.setAddressLineTwo("delhi 2");
//		address.setBilling(true);
//		address.setCity("New Delhi");
//		address.setState("New Delhi");
//		address.setPostalCode("7000001");
//		address.setCountry("India");
//		address.setUserId(user.getId());
//		assertEquals("Failed to add address", true,userDAO.addAddres(address));
//
//		if(user.getRole().equalsIgnoreCase("user")) {
//			cart = new Cart();
//			
//			cart.setUser(user);
//			
//			assertEquals("Failed to add cart",true, userDAO.addCart(cart));
//			
//			address = new Address();
////			address.setAddressLineOne("Chandigardh 1");
////			address.setAddressLineTwo("Chandigardh 2");
////			address.setBilling(true);
////			address.setCity("Chandigardh");
////			address.setState("Punjab");
////			address.setPostalCode("6000001");
////			address.setCountry("India");
////			address.setUserId(user.getId());
////			address.setShipping(true);
//			
//			address.setAddressLineOne("delhi 1");
//			address.setAddressLineTwo("delhi 2");
//			address.setBilling(true);
//			address.setCity("New Delhi");
//			address.setState("New Delhi");
//			address.setPostalCode("7000001");
//			address.setCountry("India");
//			address.setUserId(user.getId());
//			address.setShipping(true);
//			
//			assertEquals("Failed to add shipping address",true, userDAO.addAddres(address));
//
//			
//			
//			
//		}
	
	@Test
	public void testAdd() {
//		user = new User();
//		user.setFirstName("Sachin");
//		user.setLastName("Tendulkar");
//		user.setEmail("saching.t@gmail.com");
//		user.setContactNumber("9109109100");
//		user.setRole("admin");
//		user.setPassword("sachin");
		
//		user.setFirstName("Kapil");
//		user.setLastName("Dev");
//		user.setEmail("kapil.d@gmail.com");
//		user.setContactNumber("9109109111");
//		user.setRole("user");
//		user.setPassword("kapil");
		
		user = new User();
		user.setFirstName("Virendra");
		user.setLastName("Sehwag");
		user.setEmail("viru.s@gmail.com");
		user.setContactNumber("9109109112");
		user.setRole("user");
		user.setPassword("viru");
		
		if(user.getRole().equalsIgnoreCase("user")) {
			cart = new Cart();
			
			cart.setUser(user);
			
			// attach cart with user
			user.setCart(cart);
		}
		
		assertEquals("Failed to add user",true,userDAO.addUser(user));

		
	}
	
//	@Test
//	public void testCaseUpdateCart() {
//		
//		user = userDAO.getByEmail("viru.s@gmail.com");
//		cart = user.getCart();
//		
//		cart.setGrandTotal(555);
//		cart.setCartLines(2);
//		
//		assertEquals("Failed to update cart", true,userDAO.updateCart(cart));
//	}
	
	@Test
	@Ignore
	public void testCaseAddAddress() {
		//add user
		User user = new User();
		user = new User();
		user.setFirstName("Sachin");
		user.setLastName("Tendulkar");
		user.setEmail("saching.t@gmail.com");
		user.setContactNumber("9109109100");
		user.setRole("admin");
		user.setPassword("sachin");
		assertEquals("Failed to add user",true,userDAO.addUser(user));

		// add billing address
		address = new Address();
		address.setAddressLineOne("bandra 1");
		address.setAddressLineTwo("bandra 2");
		address.setBilling(true);
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setPostalCode("4000001");
		address.setCountry("India");
		address.setUserId(user.getId());
		address.setBilling(true);
		assertEquals("Failed to add billing address",true, userDAO.addAddres(address));

		// add shipping address
		address = new Address();
		address.setAddressLineOne("bandra 1 shipping");
		address.setAddressLineTwo("bandra 2 shipping");
		address.setBilling(true);
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setPostalCode("4000001");
		address.setCountry("India");
		address.setUserId(user.getId());
		address.setShipping(true);
		assertEquals("Failed to add shipping address",true, userDAO.addAddres(address));
	}
	
	@Test
	@Ignore
	public void testAddAddress() {
		
		user = userDAO.getByEmail("sachin.t@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("banglore 1");
		address.setAddressLineTwo("banglore 2");
		address.setBilling(true);
		address.setCity("Banglore");
		address.setState("Karnataka");
		address.setPostalCode("8000001");
		address.setCountry("India");
		address.setUserId(user.getId());
		address.setShipping(true);
		assertEquals("Failed to add billing address",true, userDAO.addAddres(address));
	}
		
	@Test
	@Ignore
	public void testGetAddresses() {
		
		user = userDAO.getByEmail("sachin.t@gmail.com");

		assertEquals("Failed to fetch shipping address",2,userDAO.listShippingAddress(user).size());

		assertEquals("Failed to fetch billing address","Mumbai",userDAO.getBillingAddress(user).getCity());

	}
}
