package com.scaler.demoproject.service;

import com.scaler.demoproject.dto.FakeStoreCategoryDto;
import com.scaler.demoproject.dto.FakeStoreProductDto;
import com.scaler.demoproject.exceptions.ProductNotFoundException;
import com.scaler.demoproject.model.Category;
import com.scaler.demoproject.model.Product;
import com.scaler.demoproject.repositories.CategoryRepository;
import com.scaler.demoproject.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
}private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
       Optional <Product> p = productRepository.findById(productId);
       if (p.isPresent()){
           return p.get();
       }
       throw new ProductNotFoundException("Product not found #");
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber , int pageSize) {
         Page<Product> allproducts = productRepository.findAll(PageRequest.of(pageNumber , pageSize , Sort.by("price").ascending()));
        return allproducts;
    }

    @Override
    public Product createProduct(Product product) {
        Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
        if (cat == null){
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow = categoryRepository.save(newCat);
            product.setCategory(newRow);
        }
        else {
            product.setCategory(cat);
        }
        Product saveproduct = productRepository.save(product);
        return saveproduct;
    }
     // not working
    @Override
    public Product updateproduct(Product product, Long productId) {
        Product newPro = productRepository.findById(productId).get();
        newPro.setPrice(product.getPrice());
        newPro.setImageUrl(product.getImageUrl());

         return productRepository.save(newPro);


    }


    @Override
    public List<Category> getAllCategory() {
        List <Category> cat = categoryRepository.findAll();
        return cat;
    }
  // not working properly

    public List<Product> getCategory(String category) {

       return null;
    }

}

