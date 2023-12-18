package tech.eddie.pshop.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    Integer logId;
    String consumerId;
    String productId;
    Character logType;
    Timestamp createTime;
}
