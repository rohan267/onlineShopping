package org.eshop.shoppingBackEnd.daoimpl;

import java.util.List;

import org.eshop.shoppingBackEnd.dao.ProductDAO;
import org.eshop.shoppingBackEnd.dto.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> list() {
//		String selectActiveProducts = "FROM Product WHERE active = :active and category_id=:category";
//		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProducts);
//		query.setParameter("active", true);
//		query.setParameter("category", 1);
		return sessionFactory.getCurrentSession().createQuery(" FROM Product", Product.class).getResultList();
	}

	@Override
	public Product get(int id) {
		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
			.setParameter("active", true).getResultList();
		
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active=:active and categoryId=:category";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true)
					.setParameter("category", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession().createQuery("From Product where active=:active ORDER BY id", Product.class)
				.setParameter("active", true)
				.setFirstResult(0).setMaxResults(count).getResultList();
	}

}
