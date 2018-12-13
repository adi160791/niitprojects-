package com.niit.ekart.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.niit.ekart.dao.CategoryDao;
import com.niit.ekart.dao.UsersDao;
import com.niit.ekart.model.Cart;
import com.niit.ekart.model.CartItems;
import com.niit.ekart.model.Category;
import com.niit.ekart.model.Users;

@Controller
public class UsersController
{
		@Autowired 
		  UsersDao usersDao;
		@Autowired 
		  CartDao cartDao;
		@Autowired 
		  CartItemsDao cartItemsDao;
		@Autowired 
		  CategoryDao categoryDao;
		
		
		
		
		
		@RequestMapping("/addUsers")
		public ModelAndView showform() {
			return new ModelAndView("addUsers", "command", new Users());
		}


	    @RequestMapping(value="/addUsers",method = RequestMethod.POST)  
	    public String save(@ModelAttribute("user") Users users , HttpServletRequest request,Model model) {

			boolean b = usersDao.checkUserId(users.getUserId());
			if (b == true) {
				
				Cart c=new Cart();
				c.setCartDesc(users.getUserId()+ "'s Cart");
				cartDao.addCart(c);
				users.setCart(c);
				users.setRole("ROLE_USER");
				users.setEnabled(true);
				usersDao.addUsers(users);
				return "redirect:/loginPage";
				//return new ModelAndView("redirect:/loginPage")
				
			}
			
			else {
				
				model.addAttribute("command", users);
				model.addAttribute("errorMessage", "User Id already exists. Please try again!!");
				return "viewUsers";
			}
		}
		 
		@RequestMapping(value="/editUsers/{id}")  
		    public ModelAndView editusers(@PathVariable String id)
		    {  
		        Users users=usersDao.getUsersById(id);  
		        System.out.println("n "+users.getUserId());
		        return new ModelAndView("editUsers","command",users);  
		    } 
		    
		    @RequestMapping(value="/editsaveUsers",method = RequestMethod.POST)  
		    public ModelAndView editsaveusers(@ModelAttribute("Users") Users users)
		    {  
		    	usersDao.updateUsers(users);
		        return new ModelAndView("redirect:/viewUsers");  
		    }
		    @RequestMapping("/viewUsers")
		    public ModelAndView getAllUsers(){
		    	List<Users> list=usersDao.getAllUsers();
		    	return new ModelAndView("viewUsers","list",list);
		    	
		    }
		    @RequestMapping(value = "/deleteUsers/{id}", method = RequestMethod.GET)
			public ModelAndView delete(@PathVariable String id) {
				System.out.println("delete is called");
				usersDao.deleteUsers(id);
				return new ModelAndView("redirect:/viewUsers");
			}

			@RequestMapping("/loginPage")
			public String loginPage(@RequestParam(required=false) String error,@RequestParam(required=false) String logout,Model model){
				if(error!=null)
				model.addAttribute("error","Invalid Username/Password");
				if(logout!=null)
					model.addAttribute("msg","Loggedout successfully");
				return "login";
			}
			@RequestMapping("/home")
			public String home(HttpSession session, Principal userPrincipal, Model model) {

				if(userPrincipal!=null)
				{
				String userId = userPrincipal.getName();
				Users u = usersDao.getUsersById(userId);
				Cart c = u.getCart();
				int cid = c.getCartId();
				List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
				session.setAttribute("cartSize", citem.size());
				model.addAttribute("cartSize", citem.size());
				}
				List<Category> list = categoryDao.getAllCategory();
				session.setAttribute("categoryList", list);
				model.addAttribute("categoryList", list);
				
				return "home";
			
			}

}