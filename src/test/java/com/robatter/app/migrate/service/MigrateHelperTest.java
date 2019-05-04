package com.robatter.app.migrate.service;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MigrateHelperTest {

    Logger logger = LoggerFactory.getLogger(MigrateHelperTest.class);
    @Test
    public void pickUpKey(){
        MigrateHelper.pickUpKey("product_id:${productId},product_name:${productName}");
    }

    @Test
    public void testPattern(){
        String filter = "product_info.product_id:${productId},product_info.product_name:${productName}";
        Pattern pattern = Pattern.compile("\\$\\{\\w+\\}");
        Matcher matcher = pattern.matcher(filter);
        while(matcher.find()){
            logger.info(matcher.group());
        }
    }

    @Test
    public void testUnwrapp(){
        String s = "${str}";
        logger.info(MigrateHelper.unwrap(s));
    }
}