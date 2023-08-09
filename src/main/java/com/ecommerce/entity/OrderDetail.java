package com.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	private String orderBy;
	private String orderAddress;
	private String orderContactNumber;
	private String orderAlternateNumber;
	private String orderStatus;
	private Double orderAmount;
	
	@OneToOne
	private Product product;
	
	@OneToOne
	private User user;
	
	public OrderDetail() {
		
	}
	

	public OrderDetail(String orderBy, String orderAddress, String orderContactNumber, String orderAlternateNumber,
			String orderStatus, Double orderAmount, Product product, User user) {
		super();
		this.orderBy = orderBy;
		this.orderAddress = orderAddress;
		this.orderContactNumber = orderContactNumber;
		this.orderAlternateNumber = orderAlternateNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.product = product;
		this.user = user;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderContactNumber() {
		return orderContactNumber;
	}

	public void setOrderContactNumber(String orderContactNumber) {
		this.orderContactNumber = orderContactNumber;
	}

	public String getOrderAlternateNumber() {
		return orderAlternateNumber;
	}

	public void setOrderAlternateNumber(String orderAlternateNumber) {
		this.orderAlternateNumber = orderAlternateNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	

}
