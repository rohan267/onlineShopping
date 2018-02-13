package org.eshop.shoppingBackEnd.daoimpl;

import java.util.List;

import org.eshop.shoppingBackEnd.dao.UserDAO;
import org.eshop.shoppingBackEnd.dto.Address;
import org.eshop.shoppingBackEnd.dto.Cart;
import org.eshop.shoppingBackEnd.dto.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch(Exception _ex) {
			log.error(_ex.getMessage());
			_ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addAddres(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch(Exception _ex) {
			log.error(_ex.getMessage());
			_ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getByEmail(String email) {
		String query = " from User WHERE email=:email";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, User.class).setParameter("email", email).getSingleResult();
		} catch(Exception _ex) {
			_ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Address getBillingAddress(User user) {
		String query = "From Address where user=:user and billing=:billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, Address.class)
					.setParameter("user", user).setParameter("billing", true).getSingleResult();
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String query = "From Address where user=:user and shipping=:shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, Address.class)
					.setParameter("user", user).setParameter("shipping", true).getResultList();
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return null;
		}
	}

}