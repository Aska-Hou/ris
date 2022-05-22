package edu.wku.ris.core.config.datasource;

import java.lang.annotation.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/30 21:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSwitcher {

    DataSourceEnum value() default DataSourceEnum.MASTER;

//    boolean clear() default true;
}
