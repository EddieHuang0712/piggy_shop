package tech.eddie.pshop.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    Integer salesId;
    String consumerId;
    String productId;
    Integer count;
    Double price;
    Double sum;
    Timestamp createTime;
}
