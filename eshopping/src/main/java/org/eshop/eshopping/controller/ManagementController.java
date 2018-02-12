package org.eshop.eshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.eshop.eshopping.util.FileUploadUtility;
import org.eshop.eshopping.validator.ProductValidator;
import org.eshop.shoppingBackEnd.dao.CategoryDAO;
import org.eshop.shoppingBackEnd.dao.ProductDAO;
import org.eshop.shoppingBackEnd.dto.Category;
import org.eshop.shoppingBackEnd.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger LOG = LoggerFactory.getLogger(ManagementController.class);
	@RequestMapping(value="/products")
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product newProduct = new Product();
		newProduct.setSupplierId(1);
		newProduct.setActive(true);
		mv.addObject("product",newProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","product added successfully");
			} else if(operation.equals("category")) {
				mv.addObject("message","Category submitted Successfully");
			}
		}
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
 
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
	// Add a new category from manage product page
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	/**
	 * product submission
	 */
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, 
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, bindingResult);
		}else {
			if(!mProduct.getFile().getOriginalFilename().equals(""))
				new ProductValidator().validate(mProduct, bindingResult);
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			
			return "page";
		}
		
		if(mProduct.getId() == 0)
			// create new product if id is 0
			productDAO.add(mProduct);
		else
			productDAO.update(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		LOG.debug("Product Added debug");
		LOG.info("Product added info. Prduct = "+ mProduct.toString());
		return "redirect:/manage/products?operation=product";
	}
	
	// Active/Deactive product from manage product page
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		LOG.info("Inside handleProductActivation");
		Product product = productDAO.get(id);
		
		boolean isActive=product.isActive();
		LOG.info("Before product activation status " + isActive);

		product.setActive(!isActive);
		
		productDAO.update(product);
		
		LOG.info("After product activation status " + product.isActive());

		return (isActive)?product.getName()+" successfully deactivated":product.getName()+" successfully activated";
	}
	
	@RequestMapping(value="{id}/product")
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product editedProduct = productDAO.get(id);
		mv.addObject("product",editedProduct);
		
		return mv;
	}
}
