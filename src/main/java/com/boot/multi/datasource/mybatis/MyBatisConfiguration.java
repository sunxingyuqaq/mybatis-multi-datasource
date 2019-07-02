package com.boot.multi.datasource.mybatis;

import com.boot.multi.datasource.mybatis.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 15:14
 */
@Slf4j
@Configuration
public class MyBatisConfiguration {
    public MyBatisConfiguration() {
        log.debug("Create mybatis configuration instance.");
    }

    @Configuration
    @MapperScan(basePackages = { "com.boot.multi.datasource.mybatis.dao.primary" })
    public class MapperScanPrimary {

    }

    @Configuration
    @MapperScan(basePackages = { "com.boot.multi.datasource.mybatis.dao.server" })
    public class MapperScanSecond {

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource datasource, Environment env) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(datasource);
        String config = env.getProperty("mybatis.config-location");
        sqlSessionFactoryBean.setConfigLocation(resolver.getResources(config)[0]);
        config = env.getProperty("mybatis.type-aliases-package");
        sqlSessionFactoryBean.setTypeAliasesPackage(config);
        config = env.getProperty("mybatis.mapper-locations");
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(config));
        return sqlSessionFactoryBean.getObject();
    }
}
