package com.robatter.app.migrate.manager;

public class DataSourcesTypeManager {
    private static final ThreadLocal<String> dataSourceTypes = new ThreadLocal<>();

    public static String get() {
        return dataSourceTypes.get();
    }

    /**
     * 设置数据源
     */
    public static void set(String dataSourceType) {
        dataSourceTypes.set(dataSourceType);
    }

    /**
     * 清除dataSourceKey的值
     */
    public static void remove() {
        dataSourceTypes.remove();
    }

}
