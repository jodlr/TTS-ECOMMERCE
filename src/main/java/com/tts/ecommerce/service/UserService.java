package com.tts.ecommerce.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.model.User;
import com.tts.ecommerce.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public User findByUsername(String username) {
    	return userRepository.findByUsername(username);
    }

    @Transactional
    public void saveNew(User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
    }

    public void saveExisting(User user) {
        // fill this portion in ADMIN
    }

    public User getLoggedInUser() {
    	String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();

      	return findByUsername(loggedInUserName);
    }

    public void updateCart(Map<Product, Integer> cart) {
		  // fill this portion in
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	  User user = userRepository.findByUsername(username);

    	  if(user == null) {
    		  throw new UsernameNotFoundException("Username not found");
    	  }

    	return user;  
    }
}