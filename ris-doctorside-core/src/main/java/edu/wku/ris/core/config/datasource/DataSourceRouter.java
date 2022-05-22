package edu.wku.ris.core.config.datasource;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/30 20:29
 */
public class DataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceThreadLocal.getDataSourceKey();
    }
}
