package tech.eddie.pshop.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.eddie.pshop.entity.dao.Product;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Res2Log {
    Integer logId;
    String consumerId;
    Product product;
    Character logType;
    Timestamp createTime;
}
