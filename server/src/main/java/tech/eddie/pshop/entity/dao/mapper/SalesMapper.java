package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.*;
import tech.eddie.pshop.entity.view.Res2Sales;
import tech.eddie.pshop.entity.dao.Sales;

import java.util.List;

@Mapper
public interface SalesMapper {
    /*
    public class Sales {
    Integer salesId;
    String consumerId;
    String productId;
    Integer count;
    Double price;
    Double sum;
    Timestamp createTime;
}
     */

    /**
     * 添加销售记录
     *
     * @param sales 销售记录
     */
    @Insert("INSERT INTO sales(consumer_id, product_id, count, price, sum, create_time) VALUES(#{sales.consumerId}, #{sales.productId}, #{sales.count}, #{sales.price}, #{sales.sum}, #{sales.createTime})")
    void insert(@Param("sales") Sales sales);

    /**
     * 查询某人所有购买记录
     *
     * @param consumerId 消费者id
     */
    @Results({
            @Result(property = "salesId",column = "sales_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "count",column = "count"),
            @Result(property = "price",column = "price"),
            @Result(property = "sum",column = "sum"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("SELECT * FROM sales WHERE consumer_id = #{consumerId} ORDER BY create_time DESC")
    List<Res2Sales> selectByConsumerId(@Param("consumerId") String consumerId);

    /**
     * 查询某个商家所有销售记录
     *
     * @param merchantId 商家id
     */
    @Results({
            @Result(property = "salesId",column = "sales_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "count",column = "count"),
            @Result(property = "price",column = "price"),
            @Result(property = "sum",column = "sum"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("SELECT * FROM sales WHERE product_id IN (SELECT product_id FROM product WHERE merchant_id = #{merchantId}) ORDER BY create_time DESC")
    List<Res2Sales> selectByMerchantId(@Param("merchantId") Integer merchantId);

    
}
