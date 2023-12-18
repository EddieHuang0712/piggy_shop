package tech.eddie.pshop.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    Integer cartItemId;
    String consumerId;
    String productId;
    Integer count;
    Double sum;
}
