package com.wuhe.background.controller;

import com.wuhe.background.exception.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhe
 * @create 2019/9/30 0030-上午 11:40
 */
//SpringMVC中要成为一个异常处理器，它得是@ControllerAdvice
@ControllerAdvice
public class MyExceptionHandler {

//    //1.浏览器和客户端返回都是JSON
//    @ResponseBody //json格式 返回页面
//    //要处理什么异常 我们需要@ExceptionHandler注解
//    @ExceptionHandler(UserNotExitException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexit");
//        map.put("message",e.getMessage());
//
//        return map;
//    }

    //2.转发到/error自适应效果 浏览器网页展示 客户端json
//    @ExceptionHandler(UserNotExitException.class)
//    public String handleException(Exception e, HttpServletRequest request){
//        /**
//         *传入我们自己的错误状态码，否则就不会进入定制页面的解析流程
//         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//         */
//        request.setAttribute("javax.servlet.error.status_code",500);
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexit");
//        map.put("message",e.getMessage());
//
//        return "forward:/error";
//    }

//    3.将我们定制的数据携带出去
    @ExceptionHandler(UserNotExitException.class)
    public String handleException(Exception e, HttpServletRequest request){
        /**
         *传入我们自己的错误状态码，否则就不会进入定制页面的解析流程
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexit");
        map.put("message","异常处理器定制消息");
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}
