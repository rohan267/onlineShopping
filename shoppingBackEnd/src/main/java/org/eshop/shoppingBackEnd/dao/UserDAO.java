package org.eshop.shoppingBackEnd.dao;

import java.util.List;

import org.eshop.shoppingBackEnd.dto.Address;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.User;

public interface UserDAO {

	boolean addUser(User user);

	User getByEmail(String email);
	
	boolean addAddres(Address address);
	
	Address getBillingAddress(User user);
	
	List<Address> listShippingAddress(User user);
	
	boolean updateCart(Cart cart);
}
