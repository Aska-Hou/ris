package edu.wku.ris.core.config.datasource;

import javax.sql.DataSource;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/30 20:59
 */
public class DataSourceThreadLocal {

    public static final ThreadLocal<String> dataSourceKeyThreadLocal = new ThreadLocal<>();

    public static void setDataSourceKey(String dataSourceKey){
        dataSourceKeyThreadLocal.set(dataSourceKey);
    }

    public static String getDataSourceKey(){
        return dataSourceKeyThreadLocal.get();
    }

    public static void removeDataSourceKey(){
        dataSourceKeyThreadLocal.remove();
    }
}
