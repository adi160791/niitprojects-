package com.niit.ekart.dao;

import java.util.List;

import com.niit.ekart.model.OrderDetails;
import com.niit.ekart.model.Users;

public interface OrderDetailsDao {
	
	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetails);
	public boolean updateOrderDetails(OrderDetails orderDetails);
	public boolean deleteOrderDetails(OrderDetails orderDetails);
	public boolean insertOrderDetails(OrderDetails orderDetails);
	public List<OrderDetails> getOrderDetailsListByUser (Users user);
}

