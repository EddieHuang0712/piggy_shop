package tech.eddie.pshop.entity.dao.mapper;

import org.apache.ibatis.annotations.*;
import tech.eddie.pshop.entity.dao.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    /*
    public class Product {
    String productId;
    Integer merchantId;
    String name;
    String description;
    String coverUrl;
    Double price;
    Integer stock;
}
     */

    /**
     * 添加商品
     *
     * @param product 商品
     */
    @Insert("INSERT INTO product(product_id,merchant_id, name, description, cover_url, price, stock) VALUES(#{product.productId},#{product.merchantId}, #{product.name}, #{product.description}, #{product.coverUrl}, #{product.price}, #{product.stock})")
    void insert(@Param("product") Product product);

    /**
     * 根据id查询商品
     *
     * @param productId 商品id
     */

    @Select("SELECT * FROM product WHERE product_id = #{productId}")
    Product selectByProductId(@Param("productId") String productId);

    /**
     * 查询所有商品
     */
    @Select("SELECT * FROM product LIMIT #{pageNum}, #{pageSize}")
    List<Product> selectAll(Integer pageNum, Integer pageSize);

    /**
     * 更新商品
     *
     * @param product 商品
     */
    @Update("UPDATE product SET merchant_id = #{product.merchantId}, name = #{product.name}, description = #{product.description}, cover_url = #{product.coverUrl}, price = #{product.price}, stock = #{product.stock} WHERE product_id = #{product.productId}")
    void update(@Param("product") Product product);

    /**
     * 删除商品
     *
     * @param productId 商品id
     */
    @Delete("DELETE FROM product WHERE product_id = #{productId}")
    void deleteByProductId(@Param("productId") String productId);

    /**
     * 获取商家所有商品
     *
     * @param merchantId 商家id
     */
    @Select("SELECT * FROM product WHERE merchant_id = #{merchantId}")
    List<Product> selectByMerchantId(@Param("merchantId") Integer merchantId);

    /**
     * 获取商品详情
     *
     * @param productId 商品id
     */
    @Select("SELECT * FROM product WHERE product_id = #{productId}")
    Product selectProductDetail(@Param("productId") String productId);
}
