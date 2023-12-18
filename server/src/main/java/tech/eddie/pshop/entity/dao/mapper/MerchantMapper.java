package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.eddie.pshop.entity.dao.Merchant;

@Mapper
public interface MerchantMapper {
    /*
    public class Merchant {
    Integer merchantId;
    String name;
    String phone;
    String password;
}
     */

    /**
     * 插入卖家
     *
     * @param merchant 卖家
     */
    @Insert("INSERT INTO merchant(name, phone, password) VALUES(#{merchant.name}, #{merchant.phone}, #{merchant.password})")
    void insert(@Param("merchant") Merchant merchant);

    /**
     * 根据id查询卖家
     *
     * @param merchantId 卖家id
     */
    @Select("SELECT * FROM merchant WHERE merchant_id = #{merchantId}")
    Merchant selectByMerchantId(@Param("merchantId") Integer merchantId);

    /**
     * 根据手机号查询卖家
     *
     * @param phone 手机号
     */
    @Select("SELECT * FROM merchant WHERE phone = #{phone}")
    Merchant selectByPhone(@Param("phone") String phone);
}
