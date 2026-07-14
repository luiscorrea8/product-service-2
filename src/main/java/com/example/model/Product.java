
package com.example.model;


import org.springframework.data.annotation.Id;

import lombok.Data;


/*
Agregar las anotaciones necesarias para que esta clase sea un documento de MongoDB, uso de lombok para getters, setters y constructor sin argumentos
 */
@Data
public class Product {

  @Id 
  private String id;
  private String name;
  private String category;
  private Double price;
}
