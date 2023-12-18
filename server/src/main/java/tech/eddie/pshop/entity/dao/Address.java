package tech.eddie.pshop.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    Integer addressId;
    String consumerId;
    String name;
    String email;
    String address;
}
