package com.boot.multi.datasource.mybatis.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 14:32
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceHolder.getDataSource();
        log.debug("Get dynamic data source key {}",String.valueOf(dataSource));
        return dataSource;
    }
}
