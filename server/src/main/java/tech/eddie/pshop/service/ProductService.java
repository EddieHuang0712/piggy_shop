package tech.eddie.pshop.service;

import tech.eddie.pshop.entity.dao.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts(Integer pageNum, Integer pageSize);

    void addProduct(Product product);

    void deleteProductById(String id);

    void updateProduct(Product product);

    Product getProductById(String id);

    List<Product> getProductsByMerchantId(Integer merchantId);
}
