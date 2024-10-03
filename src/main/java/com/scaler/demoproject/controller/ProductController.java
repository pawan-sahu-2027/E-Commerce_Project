package com.scaler.demoproject.controller;

import com.scaler.demoproject.dto.ErrorDto;
import com.scaler.demoproject.exceptions.ProductNotFoundException;
import com.scaler.demoproject.model.Category;
import com.scaler.demoproject.model.Product;
import com.scaler.demoproject.service.ProductService;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
@RestController
public class ProductController {

     //@Qualifier("selfProductService")
     @Qualifier ("fakeStoreProductService")
    @Autowired
    private ProductService productService;
    public ProductController ( @Qualifier("fakeStoreProductService") ProductService productService){

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
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
// - Whenever someone is doing a get request on /product/{id}
// - please execute this method
     Product currentProduct = productService.getSingleProduct (productId);
     ResponseEntity <Product> res = new ResponseEntity<>(
             currentProduct , HttpStatus.OK);

     return currentProduct;

    }


//        @GetMapping("/products/{id}")
//    public ResponseEntity <Product> getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
//// - Whenever someone is doing a get request on /product/{id}
//// - please execute this method
//     Product currentProduct = productService.getSingleProduct (productId);
//     ResponseEntity <Product> res = new ResponseEntity<>(
//             currentProduct , HttpStatus.OK);
//
//     return res;
//
//    }

    @GetMapping ("/products")
    public Page<Product> getAllProducts(@RequestParam("pageSize") int pageSize , @RequestParam("pageNumber") int pageNumber ){

       return  productService.getAllProducts(pageSize ,pageNumber);


    }

    @GetMapping("/categarys/{categary}")
    public List <Product> getProductsByCategory (@PathVariable("categary") String category){
        List <Product> c  = productService.getCategory(category);
        return c;

    }
    @GetMapping("/Categorys")
    public List<Category> AllCategory (){
        List <Category> c = productService.getAllCategory();
        return c;
    }
//  not working
    @PutMapping("/Updateproduct/{id}")
    public Product Update ( @RequestBody Product product,@PathVariable("id") Long productId ) {
    Product product1 = productService.updateproduct ( product,productId );
                   return product1 ;

    }

@ExceptionHandler(ProductNotFoundException.class)
public ResponseEntity<ErrorDto> handleProductNotFoundException (Exception e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
}

}








