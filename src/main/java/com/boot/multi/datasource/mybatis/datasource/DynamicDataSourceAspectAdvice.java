package com.boot.multi.datasource.mybatis.datasource;

import com.boot.multi.datasource.ApplicationProperties;
import com.boot.multi.datasource.mybatis.dao.primary.OpenDao;
import com.boot.multi.datasource.mybatis.dao.server.ServerDao;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 14:40
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspectAdvice {
    public DynamicDataSourceAspectAdvice() {
        log.info("Create datasource aspect advice instance.");
    }

    @Pointcut("this(com.boot.multi.datasource.mybatis.dao.Dao)")
    public void pointcut() {
        // 拦截点
    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String key = null;
        if (target instanceof OpenDao) {
            key = ApplicationProperties.PRIMARY;
        }
        if (target instanceof ServerDao) {
            key = ApplicationProperties.SECOND;
        }
        if (key != null) {
            DynamicDataSourceHolder.setDataSourceKey(key);
            log.debug("Set dynamic data source key {}", key);
        } else {
            throw new RuntimeException("Set dynamic data source set key is null.");
        }
    }

    @After("pointcut()")
    public void after(){
        log.debug("remove datasource {} success",DynamicDataSourceHolder.getDataSource());
        DynamicDataSourceHolder.removeDataSource();
    }
}
