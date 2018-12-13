package com.niit.ekart.daoImpl;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ekart.dao.UsersDao;
import com.niit.ekart.model.ShippingAddress;
import com.niit.ekart.model.Users;

@Repository("usersDao")
@Transactional
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void addUsers(Users users) 
	{
		Session session = getSession();

		session.save(users);

		session.flush();

		session.close();

	}

	@SuppressWarnings("unchecked")
	public List<Users> getAllUsers() {
		Session session = getSession();
		Query query=session.createQuery("from Users");
		List<Users> list = query.list();
		return list;
		
	}

	public Users getUsersById(String userId) {
		Session session = getSession();
		Query query=session.createQuery("FROM Users u where u.userId=:userid");
		query.setParameter("userid", userId);
		Users u=(Users)query.uniqueResult();
		return u;
	}

	public void updateUsers(Users users) {
		Session session = getSession();
		session.update(users);
		session.flush();
		session.close();
	}

	public void deleteUsers(String userId) {
		Session session = getSession();
		Query query=session.createQuery("FROM Users u where u.userId=:userid");
		query.setParameter("userid", userId);
		Users u=(Users)query.uniqueResult();
		session.delete(u);
		session.flush();
		session.close();
	}

	public boolean checkUserId(String userId)
	{
		Session session= getSession();
		Query query=session.createQuery("from Users u where u.userId=:userId");
		query.setParameter("userId", userId);
		Users u=(Users)query.uniqueResult();
		if(u==null)
		return true;
		else 
		return false;

	}

	public void addShippingAddress(ShippingAddress shippingaddress) {
		Session session = getSession();

		session.save(shippingaddress);

		session.flush();

		session.close();
	
	}

	public ShippingAddress getShippingAddressById(int id) {
	

        Session session = getSession();
        Query query=session.createQuery("FROM ShippingAddress s where s.ShippingAddressId=:shippingAddressId");
        Object shippingAddressId = null;
		query.setParameter("shippingAddressId", shippingAddressId);
        ShippingAddress s=(ShippingAddress)query.uniqueResult();
          return s;

	}

	public List<ShippingAddress> getShippingAddressByUserId(String userId) 
	{
		 Session session = getSession();
			Query query = session.createQuery("from ShippingAddress where user.userId=:userId");
			query.setParameter("userId", userId);
			List<ShippingAddress> shipingAdresList = query.list();
		        return shipingAdresList;
			
		
		
	}

}