package edu.wku.ris.core.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/30 21:00
 */
@Aspect
@Order(100)
@Component
@Slf4j
public class DataSourceEnumAspect {

    @Pointcut("@annotation(edu.wku.ris.core.config.datasource.DataSourceSwitcher)")
    public void dataSourceEnumPointCut() {

    }

    @Before(value = "dataSourceEnumPointCut()")
    public void dataSourceEnumBeforeAdvise(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = null;
        if (signature instanceof MethodSignature)
            methodSignature = (MethodSignature) signature;

        Method method = methodSignature.getMethod();

        DataSourceSwitcher annotation = method.getAnnotation(DataSourceSwitcher.class);

        if (annotation != null) {
            DataSourceEnum dataSourceEnum = annotation.value();
            DataSourceThreadLocal.setDataSourceKey(dataSourceEnum.getValue());
            log.info("[dataSourceEnumPointCut] Aop Before Result: {}", dataSourceEnum.getValue());
        }
    }


    @After(value = "dataSourceEnumPointCut()")
    public void dataSourceEnumAfterAdvise(JoinPoint joinPoint) {
        log.info("[dataSourceAutoPointCut]Aop After Finish, Method: {}, Variable：{}, Result:{}", joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()), DataSourceThreadLocal.getDataSourceKey());
        try {
            DataSourceThreadLocal.removeDataSourceKey();
        } catch (Exception exception) {
            log.error("AOP After ERROR", exception);
        } finally {
            DataSourceThreadLocal.removeDataSourceKey();
        }
    }

}
