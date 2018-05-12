package com.itrac.onlineshopping.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;
import com.itrac.onlineshopping.dao.CategoryDAO;
import com.itrac.onlineshopping.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * private static List<Category> categories = new ArrayList<Category>();
	 * 
	 * static{
	 * 
	 * Category category = new Category(); category.setId(1);
	 * category.setName("television"); category.setDescription("Entertainment");
	 * category.setImageURL("tv.1.png");
	 * 
	 * categories.add(category); //2category category = new Category();
	 * category.setId(2); category.setName("mobile");
	 * category.setDescription("Entertainment");
	 * category.setImageURL("mobile.1.png");
	 * 
	 * categories.add(category);
	 * 
	 * //3rd category category = new Category(); category.setId(3);
	 * category.setName("PC"); category.setDescription("Entertainment");
	 * category.setImageURL("pc.1.png");
	 * 
	 * categories.add(category); }
	 */

	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

		query.setParameter("active", true);

		return query.getResultList();
	}

	@Override
	public Category get(int id) {
		/*
		 * for (Category category : categories) { if (category.getId()==id) {
		 * return category; } } return null;
		 */

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
