package com.scaler.demoproject.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity // make table of this model
public class Category extends BaseModel{
   // private Long id;
   private String title;

}

