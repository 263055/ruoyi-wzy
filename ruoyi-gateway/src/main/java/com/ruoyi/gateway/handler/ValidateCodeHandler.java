package com.ruoyi.gateway.handler;

import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.gateway.service.ValidateCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 验证码获取 <br/>
 * 继承 HandlerFunction<ServerResponse> 之后，重写 handle方法 <br/>
 * 然后调用 获取验证码的方法，这样就可以通过 RouterFunctions 直接调用获取验证码方法 <br/>
 * @author ruoyi
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse>
{
    @Resource
    private ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest)
    {
        AjaxResult ajax;
        try
        {
            ajax = validateCodeService.createCaptcha();
        }
        catch (CaptchaException | IOException e)
        {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}
