package com.scaler.demoproject.model;

import com.scaler.demoproject.dto.FakeStoreProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity //make table of this model
public class Product extends BaseModel{

//    private Long id ;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST}) // if the category is not present then it will create first then perform operation top of them

    private Category category;



//    @Override
//    public String toString() {
//        return "Product{" +
//                "title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", category=" + category +
//                '}';
//    }
}


