package cn.ew.product.controller;

import cn.ew.product.bean.Product;
import cn.ew.product.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    // 查询商品
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId, HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        System.out.println(header);
        Product product = productService.getProductById(productId);
        try {
            // 模拟网络延迟
            // 测试服务熔断,当调用的服务在2秒内没有响应时，进行熔断
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

}
