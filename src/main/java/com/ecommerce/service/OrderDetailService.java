package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.OrderDetailDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.OrderDetail;
import com.ecommerce.entity.OrderInput;
import com.ecommerce.entity.OrderProductQuantity;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;


@Service
public class OrderDetailService {
	
	private static final String ORDER_PLACED = "Placed";
	
	@Autowired
	private  OrderDetailDao orderDetailDao;
	
	@Autowired 
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;	
	
	@Autowired
	private CartDao cartDao;
	
	public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
		List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantity();
		
		for(OrderProductQuantity o: productQuantityList) {
			Product product = productDao.findById(o.getProductId()).get();
			
			String currentUser = JwtRequestFilter.CURRENT_USER;
			User user= userDao.findById(currentUser).get();
			
			OrderDetail orderDetail = new OrderDetail(
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					orderInput.getAlternateNumber(),
					ORDER_PLACED,
					product.getProductDiscountedPrice()*o.getQuantity(),
					product,
					user 
				);
			
			if(!isSingleProductCheckout) {
				List<Cart> carts = cartDao.findByUser(user);
				carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
			}
			
			orderDetailDao.save(orderDetail);	
			
		}
	}
	
	public List<OrderDetail> getOrderDetails() {
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userDao.findById(currentUser).get();
		return orderDetailDao.findByUser(user);
	}
	
	public List<OrderDetail> getAllOrderDetails(String status) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		if(status.equalsIgnoreCase("All")) {
			orderDetailDao.findAll().forEach(x -> orderDetails.add(x));
		} else {
			orderDetailDao.findByOrderStatus(status).forEach(
					x -> orderDetails.add(x)
					);
		}
		
		return orderDetails;
	}
	
	public void markOrderAsDelivered(Integer orderId) {
		OrderDetail orderDetail = orderDetailDao.findById(orderId).get();
		if(orderDetail != null) {
			orderDetail.setOrderStatus("Delivered");
			orderDetailDao.save(orderDetail);
		}
	}

}
