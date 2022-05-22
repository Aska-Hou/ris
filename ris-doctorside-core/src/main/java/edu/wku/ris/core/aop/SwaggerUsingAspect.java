package edu.wku.ris.core.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/27 9:45
 */
public class SwaggerUsingAspect implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

    }
}
