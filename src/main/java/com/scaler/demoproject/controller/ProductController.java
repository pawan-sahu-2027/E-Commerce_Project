package com.scaler.demoproject.controller;

import com.scaler.demoproject.model.Product;
import com.scaler.demoproject.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductController {
    // POST / product
//    RequestBody{
//        {
//            title: 'test product',
//            price: 13.5,
//            description: 'lorem ipsum set',
//            image: 'https://i.pravatar.cc',
//            category: 'electronic'
//        }

    private ProductService productService;
    public ProductController (ProductService productService){

        this.productService = productService;
    }
    @PostMapping("/products")
    public Product createProduct (@RequestBody Product product){
// - Whenever someone is doing a post request on /products
// - please execute this method
// - creating new product
     Product postRequestResponce = productService .createProduct(product);
     return postRequestResponce;

    }
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
// - Whenever someone is doing a get request on /product/{id}
// - please execute this method
     Product currentProduct = productService.getSingleProduct (productId);
     return currentProduct;
    }
    @GetMapping ("/products")
    public List<Product> getAllProducts( ){

       return  productService.getAllProducts();

    }
}
//https://fakestoreapi.com/products
