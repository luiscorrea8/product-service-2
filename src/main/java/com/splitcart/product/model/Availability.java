
package com.splitcart.product.model;

import lombok.Data;
/*
Agregar las anotaciones de lombok para getters, setters y constructores
 */

@Data
public class Availability {

  private String sku;
  private int available;
}
