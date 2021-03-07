package com.tts.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.model.User;
import com.tts.ecommerce.service.ProductService;
import com.tts.ecommerce.service.UserService;

import lombok.Data;

@Controller
@Data
@ControllerAdvice // This makes the `@ModelAttribute`s of this controller available to all controllers, for the navbar.
public class MainController {
	@Autowired
    ProductService productService;
	
	@Autowired
    UserService userService;
	
	@GetMapping("/")
    public String main(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }
  
	@ModelAttribute("loggedInUser")
    public User loggedInUser() {
		return userService.getLoggedInUser();
	}
	
	@ModelAttribute("products")
    public List<Product> products() {
		return productService.findAll();
	}
  
    @ModelAttribute("categories")
    public List<String> categories() {
    	return productService.findDistinctCategories();
    }
  
    @ModelAttribute("brands")
    public List<String> brands() {
    	return productService.findDistinctBrands();
    }
    
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String brand,
                         Model model) {
    	
        List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
        model.addAttribute("products", filtered); // Overrides the @ModelAttribute above.
        return "main";
    }

    @GetMapping("/about")
    public String about() {
    	return "about";
    }
}
