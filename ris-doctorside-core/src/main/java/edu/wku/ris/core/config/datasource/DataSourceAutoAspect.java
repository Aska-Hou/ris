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
@Component
@Order(1)
@Slf4j
public class DataSourceAutoAspect {

    @Pointcut("execution (* edu.wku.ris.core.mapper.*.insert*(..)) || execution (* edu.wku.ris.core.mapper.*.update*(..)) || execution (* edu.wku.ris.core.mapper.*.delete*(..))")
    public void masterDataSourcePointCut() {

    }

    @Pointcut("execution (* edu.wku.ris.core.mapper.*.select*(..))")
    public void slaveDataSourcePointCut() {

    }

    @Before(value = "masterDataSourcePointCut()")
    public void masterDataSourceBeforeAdvise(JoinPoint joinPoint) {
        DataSourceThreadLocal.setDataSourceKey(DataSourceEnum.MASTER.getValue());
        log.info("[masterDataSourcePointCut] Aop Before Result: Master");
    }

    @Before(value = "slaveDataSourcePointCut()")
    public void slaveDataSourceBeforeAdvise(JoinPoint joinPoint) {
        DataSourceThreadLocal.setDataSourceKey(DataSourceEnum.SLAVE.getValue());
        log.info("[slaveDataSourcePointCut] Aop Before Result: Slave");
    }


    @After(value = "masterDataSourcePointCut() || slaveDataSourcePointCut()")
    public void dataSourceAutoAfterAdvise(JoinPoint joinPoint) {
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
