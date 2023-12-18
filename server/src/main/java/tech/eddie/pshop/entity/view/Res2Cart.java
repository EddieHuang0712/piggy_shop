package tech.eddie.pshop.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.eddie.pshop.entity.dao.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Res2Cart {
    Integer cartItemId;
    String consumerId;
    Product product;
    Integer count;
    Double sum;
}