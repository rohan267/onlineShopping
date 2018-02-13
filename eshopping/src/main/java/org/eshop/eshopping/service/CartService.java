package org.eshop.eshopping.service;

import java.util.List;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;

import org.eshop.eshopping.model.UserModel;
import org.eshop.shoppingBackEnd.dao.CartLineDAO;
import org.eshop.shoppingBackEnd.dao.ProductDAO;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.CartLine;
import org.eshop.shoppingBackEnd.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;

	@Autowired
	private ProductDAO productDAO;
	
	/**
	 * REturns the cart of logged in user
	 * @return
	 */
	
	private Cart getCart() {
		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	

	public List<CartLine> getCartLines() {
		return cartLineDAO.list(this.getCart().getId());
	}


	public String updateCartLine(int cartLineId, int count) {
		
		// fetch cart line
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		} else {
			
			Product product = cartLine.getProduct();
			
			double oldTotal = cartLine.getTotal();
			
			if(product.getQuantity() <= count) {
				count = product.getQuantity();
			}

			cartLine.setProductCount(count);	
			cartLine.setBuyingPrice(product.getPrice());
			cartLine.setTotal(product.getPrice()*count);
			
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			return "result=updated";
		}
		
	}


	public String deleteCartLine(int cartLineId) {
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		} else {
			Cart cart = this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal()-cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartLineDAO.updateCart(cart);
			
			//remove cart line
			cartLineDAO.delete(cartLine);
			return "result=deleted";
			
		}
		
	//	boolean isDeleted = cartLineDAO.delete(cartLine);
		
	//	cartLineDAO.delete(cartLine);
		
	}


	public String addCartLine(int productId) {
		String response = null;
		
		Cart cart = this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine==null) {
			//new cartline
			cartLine = new CartLine();
			
			//fetch the product to add in cart line
			Product product = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getPrice());
			cartLine.setProductCount(1); // first time adding
			cartLine.setTotal(product.getPrice());
			cartLine.setAvailable(true);

			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
			
		}
		
		return response;
	}
	
}