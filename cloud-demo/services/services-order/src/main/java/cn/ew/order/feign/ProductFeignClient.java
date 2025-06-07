package cn.ew.order.feign;

import cn.ew.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product")    // feign 客户端
public interface ProductFeignClient {


    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

}
