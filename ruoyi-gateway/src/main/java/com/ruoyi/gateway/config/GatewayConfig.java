package com.ruoyi.gateway.config;

import com.ruoyi.gateway.handler.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 网关限流配置
 * 
 * @author ruoyi
 */
@Configuration
public class GatewayConfig
{
    @Bean
    // @Order是org.springframework.core.annotation核心包中的注解，
    // 其作用定义了Spring容器加载bean的顺序。
    // 最低值具有最高优先级，这里表明下面的方法具有最高优先级
    // 即初始化最高优先级，即spring容器启动时，优先初始化添加该注解的bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
    {
        // 自定义限流异常处理
        return new SentinelFallbackHandler();
    }
}