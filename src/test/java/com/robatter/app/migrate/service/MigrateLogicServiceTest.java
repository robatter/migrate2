package com.robatter.app.migrate.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MigrateLogicServiceTest {

    Logger logger = LoggerFactory.getLogger(MigrateLogicServiceTest.class);
    @Test
    public void convertFilter() {
        String filter = "category_type=${categoryType}";
        Map<String,Object> context = new HashMap<String,Object>();
        context.put("product_category.categoryType","2");
        MigrateLogicService mls = new MigrateLogicService();
        filter = mls.convertFilter("product_category",filter,context);
        logger.info(filter);
    }
}