package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.*;
import tech.eddie.pshop.entity.view.Res2Cart;
import tech.eddie.pshop.entity.dao.CartItem;

import java.util.List;

@Mapper
public interface CartItemMapper {
    /*
    public class CartItem {
    Integer cartItemId;
    String consumerId;
    String productId;
    Integer count;
    Double sum;
}*/

    /**
     * 添加购物车商品
     *
     * @param cartItem
     */
    @Insert("INSERT INTO cart_item(consumer_id, product_id, count, sum) VALUES(#{cartItem.consumerId}, #{cartItem.productId}, #{cartItem.count}, #{cartItem.sum})")
    void insert(@Param("cartItem") CartItem cartItem);

    /**
     * 查询某人购物车所有商品
     *
     * @param consumerId
     */
    @Results({
            @Result(property = "cartItemId",column = "cart_item_id"),
            @Result(property = "consumerId",column ="consumer_id"),
            @Result(property = "product",column = "product_id",one = @One(select = "tech.eddie.pshop.entity.dao.mapper.ProductMapper.selectByProductId")),
            @Result(property = "count",column = "count"),
            @Result(property = "sum",column = "sum")
    })
    @Select("SELECT * FROM cart_item WHERE consumer_id = #{consumerId}")
    List<Res2Cart> selectByConsumerId(@Param("consumerId") String consumerId);

    /**
     * 根据id删除购物车商品
     *
     * @param cartItemId
     */
    @Delete("DELETE FROM cart_item WHERE cart_item_id = #{cartItemId}")
    void deleteByCartItemId(@Param("cartItemId") Integer cartItemId);
}
