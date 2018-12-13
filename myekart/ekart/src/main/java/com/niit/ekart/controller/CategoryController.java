
package com.niit.ekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ekart.dao.CategoryDao;
import com.niit.ekart.model.Category;


@Controller
public class CategoryController 
{
	 @SuppressWarnings("unused")
	private static final Category category = null;
	@Autowired 
	  CategoryDao categoryDao;
	 @RequestMapping("/index")  
	    public ModelAndView index(){  
	        return new ModelAndView("index");  
	    } 
	 @RequestMapping("/viewcategory")
	    public ModelAndView getAllCategory(){
	    	List<Category> list=categoryDao.getAllCategory();
	    	return new ModelAndView("viewcategory","list",list);
	    	
	    }
	 
	@RequestMapping(value="/editCategory/{id}")  
	    public ModelAndView edit(@PathVariable int id)
	    {  
	        Category category=categoryDao.getCategoryById(id);  
	        System.out.println("n "+category.getCategoryName());
	        return new ModelAndView("editCategory","command",category);  
	    } 
	    
	    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
	    public ModelAndView editsave(@ModelAttribute("category") Category category)
	    {  
	    	categoryDao.updateCategory(category);
	        return new ModelAndView("redirect:/viewcategory");  
	    }
	    
		@RequestMapping("/addCategory")  
	    public ModelAndView showform(){  
	        return new ModelAndView("addCategory","command",new Category());  
	    } 
		 @RequestMapping(value="/save",method = RequestMethod.POST)  
		    public ModelAndView save(@ModelAttribute("category") Category category)
		 {  
		    	categoryDao.addCategory(category);
		        return new ModelAndView("redirect:/viewcategory");
		    }  
		 @RequestMapping(value="/deleteCategory/{id}",method = RequestMethod.GET)  
		    public ModelAndView delete(@PathVariable int id){ 
		    	System.out.println("delete is called");
		    	categoryDao.deleteCategoryById(id);
		        return new ModelAndView("redirect:/viewcategory");  
		    }  
}
