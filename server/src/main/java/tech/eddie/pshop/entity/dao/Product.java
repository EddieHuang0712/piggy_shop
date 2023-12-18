package tech.eddie.pshop.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    String productId;
    Integer merchantId;
    String name;
    String description;
    String coverUrl;
    Double price;
    Integer stock;
}
