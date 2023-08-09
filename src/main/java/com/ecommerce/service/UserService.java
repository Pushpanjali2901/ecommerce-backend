package com.ecommerce.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.RoleDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(User user) {
		Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userDao.save(user);
	}
	
	public void initRoleAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");
		roleDao.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly registered user");
		roleDao.save(userRole);
		
		User adminUser = new User();
		adminUser.setUserName("admin123");
		adminUser.setFirstName("admin");
		adminUser.setLastName("admin");
		adminUser.setEmail("admin@gmail.com");
		adminUser.setPassword(getEncodedPassword("12345678"));
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userDao.save(adminUser);
		
		User user = new User();
		user.setUserName("pushpanjali");
		user.setFirstName("Pushpa");
		user.setLastName("Gupta");
		user.setEmail("pushpanjali@gmail.com");
		user.setPassword(getEncodedPassword("pushpa"));
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		userDao.save(user);
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);		
	}
}
