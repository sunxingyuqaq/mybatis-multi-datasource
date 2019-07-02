package com.boot.multi.datasource.mybatis.datasource;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 14:33
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> HOLDER = new InheritableThreadLocal<>();

    public static void setDataSourceKey(String datasource) {
        HOLDER.set(datasource);
    }

    public static String getDataSource() {
        return HOLDER.get();
    }

    public static void removeDataSource() {
        HOLDER.remove();
    }
}
