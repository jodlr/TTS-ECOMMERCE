package com.tts.ecommerce;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.model.User;
import com.tts.ecommerce.repository.UserRepository;
import com.tts.ecommerce.service.ProductService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ProductService prodService;
	
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cleanup database tables.
        userRepository.deleteAll();

        Product p1 = new Product();
        p1 = prodService.findById(1);       
        Product p2 = new Product();
        p2 = prodService.findById(2);       
        Product p3 = new Product();
        p3 = prodService.findById(3);
        Product p4 = new Product();
        p4 = prodService.findById(4);              
        
        Map<Product, Integer> carts = new HashMap<Product, Integer>();
        
        carts.put(p1, 3);  // quantity of 3
        carts.put(p3, 4);  // quantity of 4
        
        // Insert a user 
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode("test"));        
        user.username = "admin";
        user.cart = carts;
        
        userRepository.save(user);
        
        // Add 2nd user
        
        User u2 = new User();
        u2.setPassword(bCryptPasswordEncoder.encode("test"));
        u2.username = "abby";
        
        carts.replace(p3,4,1);
        carts.replace(p1,3,1);
        u2.cart = carts;
        
        userRepository.save(u2);
                
        
        
        
     }
}
