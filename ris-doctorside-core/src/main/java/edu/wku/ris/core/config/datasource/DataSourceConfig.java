package edu.wku.ris.core.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/30 20:17
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    // Initiate the datasource router that we design personally as the jdbc will call the determine method
    //  to decide the datasource from the data sources map.
    @Bean(name = "dataSourceRouter")
    public DataSourceRouter dataSourceRouter(@Qualifier("masterDataSource") DataSource masterDataSource,
                                             @Qualifier("slaveDataSource") DataSource slaveDataSource){
        DataSourceRouter dataSourceRouter = new DataSourceRouter();
        Map<Object, Object> routerMap = new HashMap<>();
        routerMap.put(DataSourceEnum.MASTER.getValue(), masterDataSource);
        routerMap.put(DataSourceEnum.SLAVE.getValue(), slaveDataSource);

        dataSourceRouter.setTargetDataSources(routerMap);
        dataSourceRouter.setDefaultTargetDataSource(masterDataSource);

        return dataSourceRouter;
    }

    // Initiate the sql session factory with the new dynamic datasource
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceRouter") DataSource dataSourceRouter) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceRouter);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:edu/wku/ris/core/mapper/xml/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dataSourceTx")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSourceRouter") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }
}
