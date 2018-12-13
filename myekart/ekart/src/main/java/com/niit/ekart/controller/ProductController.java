package com.niit.ekart.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ekart.dao.CategoryDao;
import com.niit.ekart.dao.ProductDao;
import com.niit.ekart.model.Category;
import com.niit.ekart.model.Product;
import com.niit.ekart.model.Users;

@SuppressWarnings("unused")
@Controller
public class ProductController 
{   
	private static final ProductDao productdao = null;
	@Autowired 
	  CategoryDao categoryDao;
	@Autowired 
	  ProductDao productDao;
	  
	@RequestMapping("/admin/productForm")
	public String showProductform(Model model) 
	{
		List<Category> categories=categoryDao.getAllCategory();
		model.addAttribute("product",new Product());
		model.addAttribute("categories",categories);
		System.out.println("Size of category list " + categories.size());
		
		return "productForm";
		//return new ModelAndView("productForm", "command", new Product());
	}

 @RequestMapping(value="/admin/saveProduct",method = RequestMethod.POST)  
	    public String saveProduct(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model,HttpServletRequest request,@RequestParam("file") MultipartFile file , HttpSession session)
	    {
	 	System.out.println("aaaaa "+ product.getProductName());
		 
		 if(result.hasErrors()){//hasErrors return true if product details in not valid
			 System.out.println(result.hasErrors());
				model.addAttribute("categories",categoryDao.getAllCategory());
				return "productForm";
			}
		 byte fileBytes[];
			FileOutputStream fos = null;

			String fileName = "";
			String productImage = "";
			ServletContext context = request.getServletContext();
			String realContextPath = context.getRealPath("/");
			String pn = product.getProductName();
			if (file != null) {
				fileName = realContextPath + "/resources/images/" + pn + ".jpg";
				productImage = "resources/images/" + pn + ".jpg";
				File fileobj = new File(fileName);
				try {
					fos = new FileOutputStream(fileobj);
					fileBytes = file.getBytes();
					fos.write(fileBytes);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			product.setProductImage(productImage);
			product.setProductStatus(true);	
			
	                productDao.addProduct(product);
	                return "redirect:/viewProduct";
		 	
	    }
 @RequestMapping("/viewProduct")
	public ModelAndView productlist() 
 {
		List<Product> listProd = productDao.getAllProducts();
		return new ModelAndView("viewProduct","productlist",listProd);
}
 
 @RequestMapping(value="/admin/saveOrUpdateProduct",method = RequestMethod.POST)  
 public String saveOrUpdateProduct(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model,HttpServletRequest request)
 {
	 
	 if(result.hasErrors()){//hasErrors return true if product details in not valid
		 System.out.println(result.hasErrors());
			model.addAttribute("categories",categoryDao.getAllCategory());
			return "productForm";
		}
	 	productDao.saveOrUpdateProduct(product);
	 	MultipartFile prodImage=product.getImage();//image uploaded in the productform.jsp
		if(prodImage!=null && !prodImage.isEmpty()){
			//how to get rootdirectory
			String rootdirectory=request.getServletContext().getRealPath("/");
			System.out.println("Root Directory " + rootdirectory);
			//create a path
			Path paths=Paths.get(rootdirectory+"\\resources\\images\\"+product.getProductId()+".png");
			System.out.println("path "+paths.toString());
				//it throws checked exception
				try {
					prodImage.transferTo(new File(paths.toString()));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return "redirect:/viewProduct";
 }
 @RequestMapping(value = "/deleteProduct{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		System.out.println("information deleted ");
		productDao.deleteProduct(id);
		return new ModelAndView("redirect:/viewProduct");
	}

 @RequestMapping(value="/viewCategoryProduct/{id}")
	public ModelAndView viewProductByCategoryID(@PathVariable int id,HttpSession session) {
		List<Product> listProdbyCid = productDao.listByCategoryId(id);
		session.setAttribute("productList", listProdbyCid);
		return new ModelAndView("viewCategoryProduct","productList",listProdbyCid);
 }

 
	@RequestMapping(value="/productDetails/{id}")  
 public ModelAndView edit(@PathVariable int id,HttpSession session){  
      Product product=productDao.getProductById(id);
      session.setAttribute("productDescription", product);
     return new ModelAndView("productDetails","productDescription",product);  
 }  
 

}