package com.scaler.demoproject.dto;

import com.scaler.demoproject.model.Category;
import com.scaler.demoproject.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String category;


    public Category Allcategory (){
    Category cat = new Category();
    cat.setTitle(category);
    return cat;
    }
}
