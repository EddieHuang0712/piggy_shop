package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.*;
import tech.eddie.pshop.entity.dao.Log;
import tech.eddie.pshop.entity.view.Res2Log;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface LogMapper {
    /*
    public class Log {
    Integer logId;
    String consumerId;
    String productId;
    Character logType;
    String createTime;
}
     */

    /**
     * 插入日志信息
     *
     * @param log
     */
    @Insert("INSERT INTO log(consumer_id, product_id, log_type, create_time) VALUES(#{log.consumerId}, #{log.productId}, #{log.logType}, #{log.createTime})")
    void insert(@Param("log") Log log);

    /**
     * 查询某人所有浏览日志
     *
     * @param consumerId
     */
    @Results({
            @Result(property = "logId",column = "log_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "logType",column = "log_type"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("SELECT * FROM log WHERE consumer_id = #{consumerId} AND log_type = '0'")
    List<Res2Log> selectBrowseHistory(@Param("consumerId") String consumerId);

    /**
     * 按日期查询某人所有购买日志
     *
     * @param consumerId
     */
    @Results({
            @Result(property = "logId",column = "log_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "logType",column = "log_type"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("SELECT * FROM log WHERE consumer_id = #{consumerId} AND log_type = '1' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Res2Log> selectPurchaseHistory(@Param("consumerId") String consumerId, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

    /**
     * 按日期查询某人所有浏览日志
     *
     * @param consumerId 用户id
     * @param startTime  开始时间
     * @param endTime    结束时间
     */
    @Results({
            @Result(property = "logId",column = "log_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "logType",column = "log_type"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("SELECT * FROM log WHERE consumer_id = #{consumerId} AND log_type = '0' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Res2Log> selectBrowseHistoryByTime(@Param("consumerId") String consumerId, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

    /**
     * 按日期查询商店销售日志
     *
     * @param merchantId 商家id
     * @param startTime  开始时间
     * @param endTime    结束时间
     */
    @Results({
            @Result(property = "logId",column = "log_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "logType",column = "log_type"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("SELECT * FROM log WHERE product_id IN (SELECT product_id FROM product WHERE merchant_id = #{merchantId}) AND log_type = '1' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Res2Log> selectSalesReport(@Param("merchantId") Integer merchantId, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
