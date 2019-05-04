package com.robatter.app.migrate.manager;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourcesTypeManager.get();
    }

}

