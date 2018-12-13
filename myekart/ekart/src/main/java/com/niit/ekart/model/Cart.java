package com.niit.ekart.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

public class Cart {
	
	@Id
	@GeneratedValue
	private int cartId;
	private String cartDesc;
	
	@OneToOne(mappedBy="cart")
	Users user;
	
	@OneToMany(mappedBy="cart")
	List<CartItems> cartItems;
	
	

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCartDesc() {
		return cartDesc;
	}

	public void setCartDesc(String cartDesc) {
		this.cartDesc = cartDesc;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) 
	{
		this.user = user;
	}

}
