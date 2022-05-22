package edu.wku.ris.core.config.invocation;

import edu.wku.ris.common.pojo.exception.MyAssert;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import org.mockito.invocation.Invocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 22:03
 */
public class TokenMockInvocationHandler implements InvocationHandler {

    private String mockToken;

    private Object proxiedObject;

    public TokenMockInvocationHandler(Object proxiedObject, String mockToken) {
        this.proxiedObject = proxiedObject;
        this.mockToken = mockToken;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyAssert.notNull(method, ResponseEnum.INNER_SERVER_ERROR);

        if (method.getName() != "checkToken"){
            return method.invoke(proxiedObject, args);
        }

        // checkToken only have one arg -> token
        MyAssert.isTrue(args.length == 1, ResponseEnum.INNER_SERVER_ERROR);

        if (args[0] instanceof String){
            String token = (String) args[0];

            if (!mockToken.equals("default") && mockToken.equals(token)){
                return true;
            }
        }

        Object result = method.invoke(proxiedObject, args);

        return result;
    }

}
