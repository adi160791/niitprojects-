package com.niit.ekart.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity

public class Users implements Serializable
{

	
	@Id
	
	@NotEmpty(message = "Please enter User Name")
	private String userId;
	@NotEmpty(message = "Please enter Password")
	private String password;
	private String role;
	public List<ShippingAddress> getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(List<ShippingAddress> shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getRole() {
		return role;
	}

	private boolean enabled;
	private String firstName;
	private String lastName;
	private String contact;
	@OneToOne
	@JoinColumn(name="cartId")
	private Cart cart;

	@OneToMany(mappedBy="user")
	private List<OrderDetails> orderDetails;
	
	@OneToMany(mappedBy="user")
	private List<ShippingAddress> shippingAddress;
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	private String userAddress;
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users()
	{
	}
}