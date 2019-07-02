package com.boot.multi.datasource;

import com.boot.multi.datasource.mybatis.datasource.DynamicDataSource;
import com.boot.multi.datasource.mybatis.datasource.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 14:25
 */
@Slf4j
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootConfiguration
public class ApplicationConfiguration {
    public ApplicationConfiguration() {
        log.debug("Create application configuration instance.");
    }

    @Bean(name = ApplicationProperties.PRIMARY)
    @Primary
    @ConfigurationProperties(ApplicationProperties.PRIMARY)
    public DataSource primary(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = ApplicationProperties.SECOND)
    @ConfigurationProperties(ApplicationProperties.SECOND)
    public DataSource second(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DynamicDataSource dynamicDataSource(@Autowired @Qualifier(ApplicationProperties.PRIMARY) DataSource primary,
                                               @Autowired(required = false)@Qualifier(ApplicationProperties.SECOND) DataSource second) {
        DynamicDataSource datasource = new DynamicDataSource();
        Map<Object, Object> dataSources = new HashMap<>(16);
        this.put(dataSources, ApplicationProperties.PRIMARY, primary);
        this.put(dataSources, ApplicationProperties.SECOND, second);
        datasource.setTargetDataSources(dataSources);
        DynamicDataSourceHolder.setDataSourceKey(ApplicationProperties.PRIMARY);
        datasource.setDefaultTargetDataSource(primary);
        return datasource;
    }

    private void put(Map<Object, Object> dataSources, String key, DataSource ds) {
        Assert.notNull(key,"key must not null");
        if (ds != null) {
            dataSources.put(key, ds);
            log.debug("Datasource {} config success.",key);
        } else {
            log.debug("Datasource {} is null.", key);
        }
    }
}
