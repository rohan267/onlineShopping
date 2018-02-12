package org.eshop.eshopping.handler;

import org.eshop.eshopping.model.RegisterModel;
import org.eshop.shoppingBackEnd.dao.UserDAO;
import org.eshop.shoppingBackEnd.dto.Address;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel registerModel) {
		String transitionValue="success";
		
		//fetch user
		User user = registerModel.getUser();
		if(user.getRole().equalsIgnoreCase("user")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode password
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// save user
		userDAO.addUser(user);
		
		// get address
		Address billing = registerModel.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		// save address
		userDAO.addAddres(billing);
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext errors) {
		String transitionValue="success";
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Confirm Password does not match").build());
			transitionValue="failure";
		}
		
		// unique email id
		if(userDAO.getByEmail(user.getEmail())!=null) {
			errors.addMessage(new MessageBuilder().error().source("email").defaultText("Email address is already used").build());
			transitionValue="failure";
		}
		return transitionValue;
	}
}
