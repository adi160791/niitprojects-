package com.niit.ekart.dao;

import java.util.List;

import com.niit.ekart.model.Product;

public interface ProductDao {
	public List<Product> getAllProducts();

	public Product getProductById(int id);

	public void saveOrUpdateProduct(Product product);
	
	public List<Product> listByCategoryId(int categoryId);
	
	public void deleteProduct(int id);
	public void updateProduct(Product product);
	public void addProduct(Product product);

	

}
