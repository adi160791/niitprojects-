package com.niit.ekart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity

public class Product {
	@Id
	@GeneratedValue
	
	private int productId;
	
	private String productName;
	
	private String productDesc;
	
	private int price;
	
	private int quantity;
	
	private String productImage;
	
	private Boolean productStatus ;
	
	
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category productCategory;

	@Transient
	private MultipartFile image;
	
	
	
	


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	


	public Category getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int  productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDesc() {
		return productDesc;
	}


	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	


	public MultipartFile getImage() {
		return image;
	}


	public void setImage(MultipartFile image) {
		this.image = image;
	}




	public Product()
	{
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public Boolean isProductStatus() {
		return productStatus;
	}


	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}



	
	
}
