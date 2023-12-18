package tech.eddie.pshop.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.eddie.pshop.entity.dao.Product;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Res2Sales {
    Integer salesId;
    String consumerId;
    Product product;
    Integer count;
    Double price;
    Double sum;
    Timestamp createTime;
}
