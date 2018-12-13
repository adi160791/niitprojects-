package com.niit.ekart.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ekart.dao.OrderDetailsDao;
import com.niit.ekart.model.OrderDetails;
import com.niit.ekart.model.Users;

@Repository("OrderDetailsDao")
@Transactional
public class OrderDetailsDaoImpl implements OrderDetailsDao 
{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}
        public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetails)
       
         {
		Session session = getSession();
		Query query=session.createQuery("FROM OrderDetails u where u.OrderDetailsId=:orderDetailsId");
		query.setParameter("orderDetailsId", orderDetails);
		OrderDetails u=(OrderDetails)query.uniqueResult();
		return u;
	}
        public boolean updateOrderDetails(OrderDetails orderDetails)
        
         {
        	try
        	{
                Session session = getSession();
		session.update(orderDetails);
		session.flush();
		session.close();
		return true;
        	}
        	catch(HibernateException e)
        	{
        		System.out.println(e.getMessage());
        		return false;
        	}
         } 
        public boolean deleteOrderDetails(OrderDetails orderDetails)
        {
        	try
        {
         Session session = getSession();
		Query query=session.createQuery("FROM OrderDetails u where OrderDetailsId=:orderDetailsId");
		query.setParameter("orderDetailsId",orderDetails.getOrderDetailsId() );
		OrderDetails u=(OrderDetails)query.uniqueResult();
		session.delete(u);
		session.flush();
		session.close();
		return true;
    	}
    	catch(HibernateException e)
    	{
    		System.out.println(e.getMessage());
    		return false;
    	}
     } 
	

        public boolean insertOrderDetails(OrderDetails orderDetails)
        {
        	try
        {
         Session session = getSession();

		session.save(orderDetails);

		session.flush();

		session.close();
		return true;
    	}
    	catch(HibernateException e)
    	{
    		System.out.println(e.getMessage());
    		return false;
    	}
      

         }
        public List<OrderDetails> getOrderDetailsListByUser (Users user)
        {
		
		Session session = getSession();
		Query query=session.createQuery("FROM OrderDetails e where e.user.userId=:userid");
		query.setParameter("userid", user.getUserId());
		List<OrderDetails> e=query.list();
		return e;
	}
}
		