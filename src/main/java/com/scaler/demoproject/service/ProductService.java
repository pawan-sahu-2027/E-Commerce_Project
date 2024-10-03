package com.scaler.demoproject.service;

import com.scaler.demoproject.exceptions.ProductNotFoundException;
import com.scaler.demoproject.model.Category;
import com.scaler.demoproject.model.Product;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
Product getSingleProduct (Long productId) throws ProductNotFoundException;
Page<Product> getAllProducts (int pageSize , int pageNumber);
Product createProduct (Product product);
Product updateproduct(  Product product,Long productId );
List <Product> getCategory (String category);
List <Category> getAllCategory();
}

