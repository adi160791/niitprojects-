package com.niit.ekart.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ekart.dao.CategoryDao;
import com.niit.ekart.model.Category;

@Repository("categoryDao")
@Transactional

public class CategoryDaoImpl implements CategoryDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	
	}
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategory()
	{
		Session session = getSession();
		Query query=session.createQuery("from Category");
		List<Category> list = query.list();
		return list;
	}

	public void addCategory(Category category) 
	{
		Session session = getSession();

		session.save(category);

		session.flush();

		session.close();
	
	}

	public Category getCategoryById(int Id) 
	{
		Session session = getSession();
		Query query=session.createQuery("FROM Category u where categoryId=:categoryId");
		query.setParameter("categoryId",Id);
		Category u=(Category)query.uniqueResult();
		return u;	
		
	}

	public Category deleteCategoryById(int CategoryId) 
	{
		
		Session session = getSession();
		Query query=session.createQuery("FROM Category u where categoryId=:categoryId");
		query.setParameter("categoryId", CategoryId);
		Category u=(Category)query.uniqueResult();
		session.delete(u);
		session.flush();
		session.close();
		return u;
	}

	public void updateCategory(Category category)
	{
		Session session = getSession();
		session.update(category);
		session.flush();
		session.close();
		
	}
	
}


	
