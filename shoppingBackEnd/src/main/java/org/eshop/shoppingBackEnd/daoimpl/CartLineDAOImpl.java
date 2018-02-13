package org.eshop.shoppingBackEnd.daoimpl;

import java.util.List;

import org.eshop.shoppingBackEnd.dao.CartLineDAO;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.CartLine;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CartLine get(int id) {
		return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		} catch(Exception _ex) {
			
		}
		return false;
	}

	@Override
	public boolean update(CartLine cartLine) {
//		String query = "update cart_line where cart id=:cartLineid";
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		} catch (HibernateException _he) {
			_he.printStackTrace();
			return false;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		} catch (HibernateException _he) {
			_he.printStackTrace();
			return false;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine where cart_id=:cartId";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId).getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine where cart_id=:cartId and available=:available";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId).
				setParameter("available", true).getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine where cart_id=:cartId AND product.id=:productId";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).
					setParameter("cartId", cartId).setParameter("productId", productId).getSingleResult();
		} catch (Exception _ex) {
			_ex	.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch(Exception _ex) {
			_ex.printStackTrace();
		}
		
		return false;
	}
}