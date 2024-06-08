package com.scaler.demoproject.model;

import com.scaler.demoproject.dto.FakeStoreProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id ;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;

//    writing getter's and setter's....
//    public String getTitle(){
//        return title;
//    }
//
//    public void setTitle(String Title){
//        this.title = title;
//    }


    }


