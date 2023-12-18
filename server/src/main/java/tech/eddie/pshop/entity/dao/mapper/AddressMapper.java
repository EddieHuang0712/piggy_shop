package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.*;
import tech.eddie.pshop.entity.dao.Address;

import java.util.List;

@Mapper
public interface AddressMapper {
    /*
    public class Address {
    Integer addressId;
    String consumerId;
    String name;
    String email;
    String address;
}
    */

    /**
     * 插入地址信息
     *
     * @param address
     */
    @Insert("INSERT INTO address(consumer_id, name, email, address) VALUES(#{address.consumerId}, #{address.name}, #{address.email}, #{address.address})")
    void insert(@Param("address") Address address);

    /**
     * 查询某人所有地址
     *
     * @param consumerId
     */
    @Select("SELECT * FROM address WHERE consumer_id = #{consumerId}")
    List<Address> selectByConsumerId(@Param("consumerId") String consumerId);

    /**
     * 根据地址id修改地址信息
     *
     * @param address
     */
    @Update("UPDATE address SET name = #{address.name}, email = #{address.email}, address = #{address.address} WHERE address_id = #{address.addressId}")
    void updateByAddressId(@Param("address") Address address);

    /**
     * 删除某个地址
     *
     * @param addressId
     */
    @Delete("DELETE FROM address WHERE address_id = #{addressId}")
    void deleteByAddressId(@Param("addressId") Integer addressId);
}
