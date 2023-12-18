package tech.eddie.pshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.eddie.pshop.entity.dao.CartItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Req4Settle {
    String consumerId;
    Integer addressId;
    List<CartItem> orders;
    Double sum;
}
