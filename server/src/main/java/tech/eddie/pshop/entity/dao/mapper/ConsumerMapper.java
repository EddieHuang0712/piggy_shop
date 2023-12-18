package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.eddie.pshop.entity.dao.Consumer;

@Mapper
public interface ConsumerMapper {
    /*
    public class Consumer {
    String consumerId;
    String username;
    String phone;
    String email;
    String password;
    String balance;
    Timestamp registerTime;
}
     */

    /**
     * 插入消费者
     *
     * @param consumer
     */
    @Select("INSERT INTO consumer(consumer_id, username, phone, email, password, balance, register_time) VALUES(#{consumer.consumerId}, #{consumer.username}, #{consumer.phone}, #{consumer.email}, #{consumer.password}, #{consumer.balance}, #{consumer.registerTime})")
    void insert(@Param("consumer") Consumer consumer);

    /**
     * 根据id查询消费者
     *
     * @param consumerId
     */
    @Select("SELECT * FROM consumer WHERE consumer_id = #{consumerId}")
    Consumer selectByConsumerId(@Param("consumerId") String consumerId);

    /**
     * 根据phone查询消费者
     *
     * @param phone
     */
    @Select("SELECT * FROM consumer WHERE phone = #{phone}")
    Consumer selectByPhone(@Param("phone") String phone);

    /**
     * 修改消费者信息
     *
     * @param consumer
     */
    @Update("UPDATE consumer SET username = #{consumer.username}, phone = #{consumer.phone}, email = #{consumer.email}, password = #{consumer.password}, balance = #{consumer.balance}, register_time = #{consumer.registerTime} WHERE consumer_id = #{consumer.consumerId}")
    void updateByConsumerId(@Param("consumer") Consumer consumer);
}
