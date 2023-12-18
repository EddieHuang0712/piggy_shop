package tech.eddie.pshop.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.eddie.pshop.utils.IdUtils;
import tech.eddie.pshop.entity.dao.Product;
import tech.eddie.pshop.entity.dao.mapper.ProductMapper;
import tech.eddie.pshop.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts(Integer pageNum, Integer pageSize) {
        return productMapper.selectAll(pageNum, pageSize);
    }

    @Override
    public void addProduct(Product product) {
        product.setProductId(IdUtils.genProductId().toString());
        productMapper.insert(product);
    }

    @Override
    public void deleteProductById(String id) {
        productMapper.deleteByProductId(id);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.update(product);
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.selectByProductId(id);
    }

    @Override
    public List<Product> getProductsByMerchantId(Integer merchantId) {
        return productMapper.selectByMerchantId(merchantId);
    }
}
