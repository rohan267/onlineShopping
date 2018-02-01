package org.eshop.shoppingBackEnd.daoimpl;

import java.util.List;

import org.eshop.shoppingBackEnd.dao.CategoryDAO;
import org.eshop.shoppingBackEnd.dto.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> list() {
		String selectActiveCategories = "FROM Category WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategories);
		query.setParameter("active", true);
		return query.getResultList();
	}

	/**
	 */
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	@Transactional
	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			
			return true;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
	}

	/**
	 * updating a single category
	 */
	@Override
	public boolean update(Category category) {
		
		try {
			sessionFactory.getCurrentSession().update(category);
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(category);
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}
		return true;
	}
}