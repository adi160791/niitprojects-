package com.niit.ekart.daoImpl;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ekart.dao.CartDao;
import com.niit.ekart.dao.UsersDao;
import com.niit.ekart.model.Cart;
import com.niit.ekart.model.CartItems;
import com.niit.ekart.model.ShippingAddress;
import com.niit.ekart.model.Users;

@Repository("cartDao")
@Transactional
public class CartDaoImpl implements CartDao
 {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void addCart(Cart cart) 
	{
		Session session = getSession();

		session.save(cart);

		session.flush();

		session.close();

	}

	
 }
