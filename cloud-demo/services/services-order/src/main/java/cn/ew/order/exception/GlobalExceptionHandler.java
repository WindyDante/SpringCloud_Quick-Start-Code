package cn.ew.order.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // 统一异常处理
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e) {
//        // 这里可以记录日志，或者返回一个统一的错误响应
//        return "发生异常: " + e.getMessage();
//    }
}
