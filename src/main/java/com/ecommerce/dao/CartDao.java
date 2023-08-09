package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {
	
	public List<Cart> findByUser(User user);

}
