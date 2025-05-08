package cn.ew.product.service.impl;

import cn.ew.product.bean.Product;
import cn.ew.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setProductName("Product " + productId);
        product.setPrice(new BigDecimal("100.00"));
        product.setNum(10);
        return product;
    }
}
