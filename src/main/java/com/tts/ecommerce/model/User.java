package com.tts.ecommerce.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.JoinColumn;



@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class User implements UserDetails {
	/**
	 * @param username
	 * @param password
	 * @param cart
	 */
	public User(@NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String password,
			Map<Product, Integer> cart) {
		this.username = username;
		this.password = password;
		this.cart = cart;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;  
	@NotNull
	@Size(max = 100)
	public String username;
	@NotNull
	@Size(max = 100)
	public String password; // this is def not how it should be saved.
	
	// per slide 4 on day 2
	@ElementCollection
	@CollectionTable(
		name = "carts", 
		joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "quantity")
	public Map<Product, Integer> cart = new HashMap<Product, Integer>();  
	   
	public Map<Product, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Product, Integer> cart) {
		this.cart = cart;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", cart=" + cart
				+ ", toString()=" + super.toString() + "]";
	}

	
}
