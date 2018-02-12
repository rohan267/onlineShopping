package org.eshop.eshopping.controller;

import javax.servlet.http.HttpSession;

import org.eshop.eshopping.model.UserModel;
import org.eshop.shoppingBackEnd.dao.UserDAO;
import org.eshop.shoppingBackEnd.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@Autowired
	HttpSession session;
	
	@Autowired
	UserDAO userDAO;
	
	private UserModel userModel;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("UserModel")==null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDAO.getByEmail(authentication.getName());
			
			if(user!=null) {
				
				userModel = new UserModel();
				
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName()+ "" + user.getLastName());
				
				if(userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}
				
				session.setAttribute("userModel", userModel);
			}
		}
		
		return (UserModel)session.getAttribute("UserModel");
	}
}
