package tech.eddie.pshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.eddie.pshop.vo.BaseRes;
import tech.eddie.pshop.entity.dao.Product;
import tech.eddie.pshop.service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/recommend")
    public BaseRes recommend(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        BaseRes res = new BaseRes();
        res.setData(productService.getAllProducts(pageNum, pageSize));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }

    @PostMapping("/add")
    public BaseRes add(@RequestBody Product product) {
        BaseRes res = new BaseRes();
        productService.addProduct(product);
        res.setCode(0);
        res.setMsg("添加成功");
        return res;
    }

    @PutMapping("/update")
    public BaseRes update(@RequestBody Product product) {
        BaseRes res = new BaseRes();
        productService.updateProduct(product);
        res.setCode(0);
        res.setMsg("更新成功");
        return res;
    }

    @DeleteMapping("/delete")
    public BaseRes delete(@RequestParam String productId) {
        BaseRes res = new BaseRes();
        productService.deleteProductById(productId);
        res.setCode(0);
        res.setMsg("删除成功");
        return res;
    }

    @GetMapping("/detail/{productId}")
    public BaseRes detail(@PathVariable String productId) {
        BaseRes res = new BaseRes();
        res.setData(productService.getProductById(productId));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }

    @GetMapping("/list/{merchantId}")
    public BaseRes list(@PathVariable Integer merchantId) {
        BaseRes res = new BaseRes();
        res.setData(productService.getProductsByMerchantId(merchantId));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }
}
