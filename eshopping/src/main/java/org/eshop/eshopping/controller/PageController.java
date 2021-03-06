package org.eshop.eshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eshop.eshopping.exception.ProductNotFoundException;
import org.eshop.shoppingBackEnd.dao.CategoryDAO;
import org.eshop.shoppingBackEnd.dao.ProductDAO;
import org.eshop.shoppingBackEnd.dto.Category;
import org.eshop.shoppingBackEnd.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		mv.addObject("userClickHome", true);
		mv.addObject("list", categoryDAO.list());
		logger.info("Inside PageController index() method info");
		logger.debug("Inside PageController index() method debug"); 
		return mv;
	}
	
	@RequestMapping(value= "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value= "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	/**
	 * show all products
	 * @return
	 */
	
	@RequestMapping(value= "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("userClickAllProducts", true);
		mv.addObject("list", categoryDAO.list());
		return mv;
	}
	
	/**
	 * show all products
	 * @return
	 */
	
	@RequestMapping(value= "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		mv.addObject("userClickCategoryProducts", true);
		mv.addObject("category", category);
		mv.addObject("list", categoryDAO.list());
		return mv;
	}
	
	/**
	 * show all products
	 * @return
	 */
	
	@RequestMapping(value= "/show/{id}/product")
	public ModelAndView showProductDetails(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		
		Product product = null;
		product = productDAO.get(id);
		
		if(product == null)
			throw new ProductNotFoundException();
		
		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title",product.getName());
		mv.addObject("userClickProductDetails", true);
		mv.addObject("product", product);
		
		return mv;
	}
	
	// Having similar mapping to our flow id
	@RequestMapping(value= "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
//		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value= "/login")
	public ModelAndView login(@RequestParam(name="error", required=false) String error, 
			@RequestParam(name="logout", required=false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		
		if(error!=null) {
			mv.addObject("message","Invalid username and/or password");
		}
		
		
		if(logout!=null) {
			mv.addObject("logout","Logout successfully");
		}
		mv.addObject("title","Login");
		return mv;
	}
	
	@RequestMapping(value= "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title","403 - Access Denied");
		mv.addObject("errorTitle","Access Denied");
		mv.addObject("errorMessage","You are not authorized to view this page!");
		return mv;
	}
	
	@RequestMapping(value= "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// fetch authentication object
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
	
//	@RequestMapping(value= {"/test"})
//		public ModelAndView test(@RequestParam("greeting") String greeting) {
//			ModelAndView mv = new ModelAndView("page");
//			mv.addObject("greeting",greeting);
//			
//			return mv;
//		}
	
//	@RequestMapping(value= "/test/{greeting}") 
//		public ModelAndView test(@PathVariable("greeting") String greeting) {
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting",greeting);
//		
//		return mv;
//	}
}
