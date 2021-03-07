package com.tts.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.model.User;
import com.tts.ecommerce.repository.UserRepository;
import com.tts.ecommerce.service.ProductService;
import com.tts.ecommerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
public class CartController {
  @Autowired
  ProductService productService;

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;
  
  @ModelAttribute("loggedInUser")
  public User loggedInUser() {
	  return userService.getLoggedInUser(); 
  }

  @ModelAttribute("cart")
  public Map<Product, Integer> cart() {
	  User user = userService.getLoggedInUser();
	  if (user != null) {
		  return user.getCart();   
	  } else {
		  return new HashMap<Product, Integer>(); 
	  }
  }
  @ModelAttribute("total")
  public float total() {
	  float total = 0f;
	  User user = userService.getLoggedInUser();
	  if (user != null) {
		  Map<Product, Integer> cart = user.getCart();
		  for (Map.Entry<Product,Integer> loopCart: cart.entrySet()) { 
			  Product product = loopCart.getKey();
		  	  total = total + product.price;
		  }
	  }
	  return total;
  }
  /**
   * Puts an empty list in the model that thymeleaf can use to sum up the cart total.
   */
  @ModelAttribute("list")
  public List<Double> list() {
      return new ArrayList<>();
  }

  @GetMapping("/cart")
  public String showCart() {
      return "cart";
  }

  @PostMapping("/cart")
  public String addToCart(@RequestParam long id) {

	  Product p = productService.findById(id);   
	  User u = userService.getLoggedInUser();

	  if (u != null ) {
		  Map<Product, Integer> cart = u.getCart();
		  if (cart.get(p) != null) {
			  cart.put(p, cart.get(p) + 1);
		  } else {
			  cart.put(p, 1);  
		  }
	      u.setCart(cart);
	      userRepository.save(u);
	  }
	  return null;
  }

  @PatchMapping("/cart")
  public String updateQuantities(@RequestParam long[] id, @RequestParam int[] quantity) {
      // fill this portion in
      return "cart";
  }
	@DeleteMapping("/cart")
	public String removeFromCart(@RequestParam long id) {
	  Product p = productService.findById(id);   
	  User u = userService.getLoggedInUser();

	  if (u != null ) {
		  Map<Product, Integer> cart = u.getCart();
	      cart.remove(p);
		  u.setCart(cart);
	      userRepository.save(u);
	  }

	  return null;
	}

	//private void setQuantity(Product p, int quantity)
		// fill this portion in
}