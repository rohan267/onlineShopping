package org.eshop.eshopping.controller;

import org.eshop.eshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result", required=false) String result) {
		System.out.println("Cart Controller called");
		ModelAndView mv = new ModelAndView("page");
		
		if(result!=null) {
			switch(result) {
			case "error":
				mv.addObject("message","Error!");
				break;
			case "updated":
				mv.addObject("message","Cart updated successfully");
				break;
			case "deleted":
				mv.addObject("message","Cart deleted");
				break;
			case "added":
				mv.addObject("message","Product is addedd successfully");
				break;
			}
		}
		
		mv.addObject("title","User Cart");
		mv.addObject("userClickCart",true);
		mv.addObject("cartLines",cartService.getCartLines());
		
		return mv;
	}
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCartLine(@PathVariable int cartLineId, @RequestParam("count") int count) {
		
		String response = cartService.updateCartLine(cartLineId, count);
		
		return "redirect:/cart/show?"+response;
	}

	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable int cartLineId) {
		
		 String response = cartService.deleteCartLine(cartLineId);

		 return "redirect:/cart/show?"+response;
	}
}