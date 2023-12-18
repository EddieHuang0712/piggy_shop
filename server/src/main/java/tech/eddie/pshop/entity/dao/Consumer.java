package tech.eddie.pshop.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    String consumerId;
    String username;
    String phone;
    String email;
    String password;
    String balance;
    Timestamp registerTime;
}
