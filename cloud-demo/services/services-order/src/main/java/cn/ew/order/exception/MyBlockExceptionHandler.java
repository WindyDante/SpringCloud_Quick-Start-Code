package cn.ew.order.exception;

import cn.ew.common.R;
import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String resourceName, BlockException e) throws Exception {
        // Sentinel 在重启后，簇点链路中的规则也会重启，需要重新设置!!!
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        R err = R.err(500, resourceName + "被Sentinel限流了，请稍后再试,原因为:" + e.getClass());
        // 返回json
        String res = objectMapper.writeValueAsString(err);
        writer.write(res);
        writer.close();
    }
}
