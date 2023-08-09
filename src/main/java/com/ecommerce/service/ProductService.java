package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

@Service
public class ProductService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CartDao cartDao;
	
	public Product addNewProduct(Product product) {
		return productDao.save(product);
	}

	public List<Product> getAllProducts(String searchKey) {
		if(searchKey.equals("")) {
			return (List<Product>)productDao.findAll();
		} else {
			return (List<Product>)productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey);
		}
	}
	
	public void deleteProductDetails(Integer productId) {
		productDao.deleteById(productId);
	}
	
	public Product getProductDetailsById(Integer productId) {
		return productDao.findById(productId).get();
	}
	
	public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
		if(isSingleProductCheckout && productId!=0) {
			List<Product> list = new ArrayList<>();
			Product product = productDao.findById(productId).get();
			list.add(product);
			return list;
		} else {
			String username = JwtRequestFilter.CURRENT_USER;
			User user = userDao.findById(username).get();
			List<Cart> carts = cartDao.findByUser(user);
			return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
		}
	}
}
