package com.niit.ekart.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ekart.dao.CartDao;
import com.niit.ekart.dao.CartItemsDao;
import com.niit.ekart.dao.OrderDetailsDao;
import com.niit.ekart.dao.ProductDao;
import com.niit.ekart.dao.UsersDao;
import com.niit.ekart.model.Cart;
import com.niit.ekart.model.CartItems;
import com.niit.ekart.model.OrderDetails;
import com.niit.ekart.model.Product;
import com.niit.ekart.model.ShippingAddress;
import com.niit.ekart.model.Users;

@Controller
public class CartController {

	@Autowired
	CartItemsDao cartItemsDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	UsersDao userDao;

	@Autowired
	OrderDetailsDao orderDetailsDao;
	@Autowired
	CartDao cartDao;


	@RequestMapping("/cart/viewCart")
	public ModelAndView showCart(Principal principal) {

		int total = 0;
		String userId = principal.getName();
		System.out.println(userId);
		Users u = userDao.getUsersById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
		System.out.println(cid);
		List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
		System.out.println(list.isEmpty());
		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			CartItems citem = (CartItems) itr.next();
			total = total + citem.getSubTotal();
		}
		System.out.println(total);
		ModelAndView mview = new ModelAndView("viewCart");
		mview.addObject("listC", list);
		mview.addObject("total", total);
		return mview;
	}
	
	
	@RequestMapping("/cart/addtocart/{id}")
	public ModelAndView addToCart(@PathVariable int id, Principal principal,@RequestParam int requestedQuantity,HttpSession session) {

		String userId = principal.getName();
		
		Users u = userDao.getUsersById(userId);
		
		Cart c = u.getCart();
		int cid = c.getCartId();
		
		System.out.println("hello");
		Product product = productDao.getProductById(id);
		System.out.println("hello");
		int p = product.getPrice();
		List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
		Iterator<CartItems> itr = list.iterator();

		while (itr.hasNext()) {
			CartItems citem = (CartItems) itr.next();
			System.out.println(citem.getProduct().getProductId() + citem.getCart().getCartId() + id);

			if (citem.getProduct().getProductId()==id ) {
				System.out.println("Match Found");
				citem.setQty(citem.getQty() + requestedQuantity);
				citem.setSubTotal(citem.getQty() * p);
				cartItemsDao.updateCartItems(citem);
				return new ModelAndView("redirect:/cart/viewCart");
			}
		}

		CartItems ci = new CartItems();
		ci.setQty(requestedQuantity);
		ci.setStatus(true);
		ci.setProduct(product);
		ci.setSubTotal(requestedQuantity*p);
		ci.setCart(c);
		cartItemsDao.addCartItems(ci);
		
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		
		return new ModelAndView("redirect:/cart/viewCart");

	}
	
	
	
	@RequestMapping(value = "/cart/deleteCart/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,HttpSession session,Principal userPrincipal) {
		System.out.println("delete is called");
		cartItemsDao.deleteCartItems(id);
		String userId = userPrincipal.getName();
		Users u = userDao.getUsersById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		return new ModelAndView("redirect:/cart/viewCart");
	}
	
	@RequestMapping("/cart/checkout")
	public ModelAndView showShipmentform(Principal principal) {
		
		String userId = principal.getName();
		List<ShippingAddress> list=userDao.getShippingAddressByUserId(userId);
		if(list.isEmpty())
		{
			return new ModelAndView("shippingAddressForm", "command", new ShippingAddress());
		}
		else
		{
			
			return new ModelAndView("selectShippingAddressForm", "shipaddress", list);
		}
		
	}
	
	@RequestMapping("/cart/getshippingform")
	public ModelAndView addNewShipAddress(Principal principal) {

		
		return new ModelAndView("shippingAddressForm", "command", new ShippingAddress());
	}

	
	
	@RequestMapping("/cart/createorder")
	public String saveOrder(@ModelAttribute ShippingAddress shippingaddress, Model model, Principal principal,HttpSession session) {

		
		String userId = principal.getName();
		Users u = userDao.getUsersById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
	       List<CartItems> list1 = cartItemsDao.getCartItemsByCartId(cid);
	       Iterator itr = list1.iterator();
			while(itr.hasNext())
			{
				CartItems citem = (CartItems) itr.next();
				Product p=citem.getProduct();
				if((p.getQuantity()-citem.getQty())>=0)
				{
					System.out.println(p.getQuantity()-citem.getQty());
					p.setQuantity(p.getQuantity()-citem.getQty());
					productDao.updateProduct(p);
				}
				else
				{
					cartItemsDao.deleteCartItems(citem.getCartItemId());
					List<CartItems> ci = cartItemsDao.getCartItemsByCartId(cid);
					session.setAttribute("cartSize", ci.size());
					model.addAttribute("product",p);
					model.addAttribute("error","product out of stock");
					return "redirect:/cart/showCart";
				}
			}
			
			List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
			Iterator itr1 = list.iterator();
			int grandTotal=0;
		       for(CartItems cartitem:list){//For each CartItem in list of cartitems
		    	   grandTotal=grandTotal+cartitem.getSubTotal();
		       }
		
		shippingaddress.setUser(u);
		userDao.addShippingAddress(shippingaddress);   
		OrderDetails orderD=new OrderDetails();
		orderD.setOrderDetailsStatus("Ordered");
		orderD.setOrderDetails(new Date());
		orderD.setUser(u);
		orderD.setGrandTotal(grandTotal);
		orderDetailsDao.insertOrderDetails(orderD);
		
		

		while (itr1.hasNext()) {
			CartItems citem = (CartItems) itr1.next();
			if (citem.isStatus()) {
				System.out.println("Match Found");
				citem.setOrderDetails(orderD);
				cartItemsDao.updateCartItems(citem);
			}
			citem.setStatus(false);
			cartItemsDao.updateCartItems(citem);
		}
		
		System.out.println(orderD.getOrderDetailsId());
		List<CartItems> orderedList = cartItemsDao.getCartItemsByOrderId(orderD.getOrderDetailsId());
		
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		
		System.out.println(orderedList.isEmpty());
		model.addAttribute("order",orderD);
		model.addAttribute("shipping", shippingaddress);
		model.addAttribute("cartItem", orderedList);
		return "invoice";
	}
	
	/*@RequestMapping("/invoice")
	public ModelAndView finalPage() {

		return new ModelAndView("invoice");
	}*/
	
	@RequestMapping(value = "/cart/generateinvoice/{id}", method = RequestMethod.GET)
	public String generateInvoice(@PathVariable int id,Model model,HttpSession session,Principal principal) {
		
	
		String userId = principal.getName();
		Users u = userDao.getUsersById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
	       List<CartItems> list1 = cartItemsDao.getCartItemsByCartId(cid);
	       Iterator itr = list1.iterator();
			while(itr.hasNext())
			{
				CartItems citem = (CartItems) itr.next();
				Product p=citem.getProduct();
				if((p.getQuantity()-citem.getQty())>=0)
				{
					System.out.println(p.getQuantity()-citem.getQty());
					p.setQuantity(p.getQuantity()-citem.getQty());
					productDao.updateProduct(p);
				}
				else
				{
					cartItemsDao.deleteCartItems(citem.getCartItemId());
					List<CartItems> ci = cartItemsDao.getCartItemsByCartId(cid);
					session.setAttribute("cartSize", ci.size());
					model.addAttribute("product",p);
					model.addAttribute("error","product out of stock");
					return "redirect:/cart/showCart";
				}
			}
			
			List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
			Iterator itr1 = list.iterator();
			int grandTotal=0;
		       for(CartItems cartitem:list){//For each CartItem in list of cartitems
		    	   grandTotal=grandTotal+cartitem.getSubTotal();
		       }
		       
		OrderDetails orderD=new OrderDetails();
		orderD.setOrderDetailsStatus("Ordered");
		orderD.setOrderDetails(new Date());
		orderD.setUser(u);
		orderD.setGrandTotal(grandTotal);
		orderDetailsDao.insertOrderDetails(orderD);
		
		

		while (itr1.hasNext()) {
			CartItems citem = (CartItems) itr1.next();
			if (citem.isStatus()) {
				System.out.println("Match Found");
				citem.setOrderDetails(orderD);
				cartItemsDao.updateCartItems(citem);
			}
			citem.setStatus(false);
			cartItemsDao.updateCartItems(citem);
		}
		
		System.out.println(orderD.getOrderDetailsId());
		List<CartItems> orderedList = cartItemsDao.getCartItemsByOrderId(orderD.getOrderDetailsId());
		
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		
		System.out.println(orderedList.isEmpty());
		model.addAttribute("order",orderD);
		model.addAttribute("shipping", userDao.getShippingAddressById(id));
		model.addAttribute("cartItem", orderedList);
		return "invoice";
		
	}
	

}
