package edu.wku.ris.core.config.invocation;

import edu.wku.ris.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @author Aska
 * @description 动态增强tokenService 传入的token可加入白名单内 调用checkToken方法时直接过滤返回true
 * @create 2022/4/26 13:53
 */
public class TokenServiceMockProxy {

    private static TokenService tokenServiceProxy;

    public static TokenService getProxy(TokenService tokenService, String mockToken){
        if (tokenServiceProxy == null){
            TokenMockInvocationHandler tokenMockInvocationHandler = new TokenMockInvocationHandler(tokenService, mockToken);
            TokenService proxyTokenService = (TokenService) Proxy.newProxyInstance(tokenService.getClass().getClassLoader(), tokenService.getClass().getInterfaces(), tokenMockInvocationHandler);
            tokenServiceProxy = proxyTokenService;
            return proxyTokenService;
        }
        else {
            return tokenServiceProxy;
        }
    }

}
