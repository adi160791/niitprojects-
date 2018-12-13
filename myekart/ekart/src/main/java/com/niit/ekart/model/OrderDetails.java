package com.niit.ekart.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderDetails {

	@Id
	@GeneratedValue
	private int orderDetailsId;
	private String OrderDetailsStatus;
	private int grandTotal;

	

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	@OneToMany(mappedBy = "orderDetails")
	List<CartItems> cartItems;

	private Date orderDetails;

	@ManyToOne
	@JoinColumn(name = "userId")
	Users user;

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public String getOrderDetailsStatus() {
		return OrderDetailsStatus;
	}

	public void setOrderDetailsStatus(String orderDetailsStatus) {
		OrderDetailsStatus = orderDetailsStatus;
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public Date getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Date orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
