package com.tts.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
    	return productRepository.findAll();
	}

    public Product findById(long id) {
    	return productRepository.findById(id);
    }

    public List<String> findDistinctBrands() {
		return productRepository.findDistinctBrands();
    }

    public List<String> findDistinctCategories() {
		return productRepository.findDistinctCategories();
    }

    public void save(Product product) {
    	productRepository.save(product);    	
    }

    public void deleteById(long id) {
          // fill this portion in
    	productRepository.deleteById(id);
    }

    public List<Product> findByBrandAndOrCategory(String brand, String category) {
    	return productRepository.findByBrandOrCategory(brand, category);
    }
}