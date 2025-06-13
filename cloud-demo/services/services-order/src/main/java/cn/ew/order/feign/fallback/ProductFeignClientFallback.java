package cn.ew.order.feign.fallback;

import cn.ew.order.feign.ProductFeignClient;
import cn.ew.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    // 只需要让对应的result实现该接口就可以进行兜底处理,如果原接口失败了,返回默认结果

    /**
     * 兜底回调方法
     * 当远程调用失败时，执行此方法
     *
     * @param id 商品ID
     * @return 默认的商品信息
     */
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调");
        Product product = new Product();
        product.setProductName("default");
        product.setId(1L);
        product.setNum(1);
        product.setPrice(BigDecimal.valueOf(0.0));
        return product;
    }
}
