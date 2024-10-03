package com.scaler.demoproject.service;

import com.scaler.demoproject.dto.CategoryDto;
import com.scaler.demoproject.dto.FakeStoreCategoryDto;
import com.scaler.demoproject.dto.FakeStoreProductDto;
import com.scaler.demoproject.exceptions.ProductNotFoundException;
import com.scaler.demoproject.model.Category;

import com.scaler.demoproject.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;
    public FakeStoreProductService (RestTemplate restTemplate , RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//       it will return only the product
        Product productFromRedis = (Product) redisTemplate.opsForHash().get("PRODUCTS" , "PRODUCT_" + productId);
        // redis returning obj and we are expecting product // At firstplace we are putting table name to search for  and at second place key
        if (productFromRedis != null){
            return productFromRedis;
        }
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/"
                     + productId   , FakeStoreProductDto.class);

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product not found" + " with id " + productId);
        }
        redisTemplate.opsForHash().put("PRODUCTSaddad", "PRODUCT_"+ productId , fakeStoreProductDto.toProduct());
        return fakeStoreProductDto.toProduct();
    }


 //         it will return ResponseEntity
//    @Override
//    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//
//       ResponseEntity <FakeStoreProductDto> fakeStoreProductDto =
//                restTemplate .getForEntity("https://fakestoreapi.com/products/"
//                        + productId,FakeStoreProductDto.class);
//
//          if (fakeStoreProductDto.getStatusCode() == HttpStatus.NOT_FOUND){
//             throw new ProductNotFoundException("Product not found" + "with id" + productId);
//
//          }
//
//        return fakeStoreProductDto.getBody().toProduct();
//
//    }



    @Override
    public Page<Product> getAllProducts( int pageNumber , int pageSize) {
//        List <Product> products = new ArrayList<>();
//        FakeStoreProductDto [] res  =
//                restTemplate .getForObject("https://fakestoreapi.com/products" , FakeStoreProductDto[].class);
//         for (FakeStoreProductDto fs : res) {
//             products.add(fs.toProduct());
//         }
//
//        return products;
        return null;
    }

            @Override
            public Product createProduct (Product product){
                FakeStoreProductDto fs = new FakeStoreProductDto();
                fs.setId(product.getId());
                fs.setTitle(product.getTitle());
                fs.setCategory(product.getCategory().getTitle());
                fs.setImage(product.getImageUrl());
                fs.setDescription(product.getDescription());
                fs.setPrice(product.getPrice());
                FakeStoreProductDto response = restTemplate.postForObject(
                        "https://fakestoreapi.com/products",
                        fs, FakeStoreProductDto.class
                );
                return response.toProduct();
            }



    @Override
    public Product updateproduct ( Product product,Long productId )  {
        FakeStoreProductDto fs = new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        //fs.getCategory(new C)
        fs.setImage(product.getImageUrl());
        fs.setDescription(product.getDescription());
        fs.setPrice(product.getPrice());
        FakeStoreProductDto obj  = restTemplate.postForObject(
                "https://fakestoreapi.com/products/" ,
                fs , FakeStoreProductDto.class
        );

          return null;
    }






    @Override
    public List <Product> getCategory (String category){
        List<Product> product = new ArrayList<>();
         FakeStoreCategoryDto[] res = restTemplate.getForObject("https://fakestoreapi.com/products/category/jewelery" , FakeStoreCategoryDto[].class);
         for (FakeStoreCategoryDto fs : res){
              product.add(fs.toCategory());
         }
         return product;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> cat = new ArrayList<>();
         CategoryDto[] res = restTemplate.getForObject("https://fakestoreapi.com/products/categories" , CategoryDto[].class);
         for (CategoryDto fs: res){
             cat.add(fs.Allcategory());
         }
         return cat;

    }




//    @Override
//    public void updateProductViaPut(Long productId, Product product)
//    {
//        //step 1: product to fakestoreProductDto conversion
//        FakeStoreProductDto fakeStoreProductDto = product.toFakeStoreProductDto();
//
//        restTemplate.put("https://fakestoreapi.com/products/"+productId, fakeStoreProductDto);
//    }

}
