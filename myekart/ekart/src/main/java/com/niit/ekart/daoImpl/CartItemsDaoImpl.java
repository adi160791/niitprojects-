package com.niit.ekart.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ekart.dao.CartItemsDao;
import com.niit.ekart.model.CartItems;
import com.niit.ekart.model.Users;

@Repository("CartItemsDao")
@Transactional
public class CartItemsDaoImpl implements CartItemsDao 
{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void addCartItems(CartItems cartitems) 
	{
		Session session = getSession();

		session.save(cartitems);

		session.flush();

		session.close();

	}
       @SuppressWarnings("unchecked")
	public List<CartItems> getAllCartItems() 
{
		Session session = getSession();
		Query query=session.createQuery("from CartItems");
		List<CartItems> list = query.list();
		session.close();
		return list;
		
	}
        public void updateCartItems(CartItems cartitems) {
		Session session = getSession();
		session.update(cartitems);
		session.flush();
		session.close();
	}
    
         public List<CartItems> listByCartItemId(int CartItemsId)
	{
		
		Session session = getSession();
		Query query=session.createQuery("FROM CartItems e where e.CartItemsId=:cartItemsid");
		query.setParameter("cartItemsid", CartItemsId);
		List<CartItems> e=query.list();
		session.close();
		return e;
	}
          public List<CartItems> getCartItemsByOrderId(int orderDetailsId)
	{
		
		Session session = getSession();
		Query query=session.createQuery("FROM CartItems  where orderDetails.orderDetailsId=:orderDetailsId");
		query.setParameter("orderDetailsId", orderDetailsId);
		List<CartItems> e=query.list();
		session.close();
		return e;
	}

		public List<CartItems> getCartItemsByCartId(int cartId) {
			 Session session = getSession();
		        Query query=session.createQuery("FROM CartItems c where c.cart.cartId=:cartId and status =true");
		        query.setParameter("cartId", cartId);
		        List<CartItems> cartItemList = query.list();
		        session.close();
		        return cartItemList;
	

		}

		public void deleteCartItems(int cartItemId) {
			Session session = getSession();
			Query query=session.createQuery("FROM CartItems c where cartItemId=:cartItemId");
			query.setParameter("cartItemId", cartItemId);
			CartItems c=(CartItems)query.uniqueResult();
			session.delete(c);
			session.flush();
			session.close();
			
		}

}