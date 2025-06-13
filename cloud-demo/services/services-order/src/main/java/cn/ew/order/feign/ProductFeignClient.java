package cn.ew.order.feign;

import cn.ew.order.feign.fallback.ProductFeignClientFallback;
import cn.ew.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// fallback指定一旦服务失败了,通过谁来进行兜底数据获取
@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class)    // feign 客户端
public interface ProductFeignClient {


    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

}
