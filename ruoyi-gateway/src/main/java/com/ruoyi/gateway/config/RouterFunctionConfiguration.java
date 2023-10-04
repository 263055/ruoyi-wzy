package com.ruoyi.gateway.config;

import com.ruoyi.gateway.handler.ValidateCodeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import javax.annotation.Resource;

/**
 * 路由配置信息
 * 
 * @author ruoyi
 */
@Configuration
public class RouterFunctionConfiguration
{
    @Resource
    private ValidateCodeHandler validateCodeHandler;

    /**
     * 这段代码的作用是：<br/>
     * 以一种可发现的方法，通过一个构造器接口，创建一个路由功能 <br/>
     * 并且将 code 路由 与 获取验证码进行匹对 <br/>
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public RouterFunction routerFunction()
    {
        // 将匹配 /code且接收 text_plain的请求，交给validateCodeHandler处理
        // text/plain，表示我们要发送纯文本数据
        return RouterFunctions.route(
                RequestPredicates.GET("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                validateCodeHandler);
    }
}
