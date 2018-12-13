package com.niit.ekart.dao;


import java.util.List;

import com.niit.ekart.model.CartItems;

public interface CartItemsDao {
	public void addCartItems(CartItems cartItems);
	public List<CartItems> getAllCartItems();
	public void updateCartItems(CartItems cartItems);
	public List<CartItems> getCartItemsByCartId(int cartId);
	public void deleteCartItems(int cartItemId);
	public List<CartItems> getCartItemsByOrderId(int cid);

}
