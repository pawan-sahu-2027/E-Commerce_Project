package com.scaler.demoproject.service;

import com.scaler.demoproject.dto.FakeStoreProductDto;
import com.scaler.demoproject.model.Category;
import com.scaler.demoproject.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    public FakeStoreProductService (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate .getForObject("https://fakestoreapi.com/products/1"
                + productId,FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }



//    public List <Product> getAllProducts() {
//        FakeStoreProductDto [] productArray = restTemplate .getForObject("https://fakestoreapi.com/products" , FakeStoreProductDto [] .class );
//
//        return Arrays.asList();
//    }


    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto [] listOfProduct  = restTemplate.getForObject("https://fakestoreapi.com/products" , FakeStoreProductDto[].class);
        ArrayList<Product> ar = new ArrayList<>();
        for (FakeStoreProductDto x : listOfProduct){
            Product product = new Product();
            product.setId(x.getId());
            product.setImageUrl(x.getImage());
            product.setDescription(x.getDescription());
            product.setPrice(x.getPrice());
//
//            Category ca = new Category();
//            ca.setTitle(Category);
//            product.setTitle(Category);

            ar.add(product);
        }
        return ar;

    }


    @Override
    public Product createProduct(Product product) {
         FakeStoreProductDto fs = new FakeStoreProductDto();
         fs.setId(product.getId());
         fs.setTitle(product.getCategory().getTitle());
         fs.setImage(product.getImageUrl());
         fs.setDescription(product.getDescription());
         fs.setPrice(product.getPrice());
         FakeStoreProductDto response = restTemplate.postForObject(
                 "https://fakestoreapi.com/carts",
                 fs, FakeStoreProductDto.class
         );
         return response.toProduct();
    }

}
