package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.OrderDetail;
import com.ecommerce.entity.User;


@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {

	public List<OrderDetail> findByUser(User user);
	
	public List<OrderDetail> findByOrderStatus(String status);
}
