package com.boot.multi.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 14:26
 */
@ConfigurationProperties(prefix = "boot")
public class ApplicationProperties {

    public static final String PRIMARY = "boot.datasource.primary";

    public static final String SECOND = "boot.datasource.second";

    /**
     * 数据源配置。
     */
    private Map<String, DataSourceProperties> datasource = new LinkedHashMap<>();

    public Map<String, DataSourceProperties> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DataSourceProperties> datasource) {
        this.datasource = datasource;
    }
}
