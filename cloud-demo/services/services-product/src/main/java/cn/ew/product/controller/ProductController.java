package cn.ew.product.controller;

import cn.ew.product.bean.Product;
import cn.ew.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    // 查询商品
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return product;
    }

}
