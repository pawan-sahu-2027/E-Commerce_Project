package com.scaler.demoproject.repositories;

import com.scaler.demoproject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;



import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Long> {
    // this is a JPA declare query
    Product save (Product product);
   // Product findByTitle(String title);
   //  Product deleteProductBy(String title);

   Page<Product> findAll(Pageable pageable);//interface help in finding gatPageNumber , getOffset , getDort
   List<Product> findBy();
   // List of product as well as other information related to pagination
   // that we will pass getTotalPages , getTotalElements
   /// this is a HQL declare query
    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> getProductByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "select * from product p where p.category_id = categoryId" , nativeQuery = true)
    List<Product> getProductByCategoryIdWithNativeQueries (@Param("categoryId") Long categoryId);

}
