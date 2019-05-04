package com.robatter.app.migrate.service;


import com.robatter.app.migrate.dao.MigrateDao;
import com.robatter.app.migrate.dto.MigrateQueryDto;
import com.robatter.app.migrate.dto.RowDataDto;
import com.robatter.app.migrate.dto.TableRefDto;
import com.robatter.app.migrate.inits.ResourcesInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MigrateProcessService {

    Logger logger = LoggerFactory.getLogger(MigrateProcessService.class);

    MigrateDao migrateDao;

    @Autowired
    MigrateLogService migrateLogService;

    @Autowired
    ResourcesInit resourcesInit;

    @Autowired
    MigrateLogicService migrateLogicService;

    public void migrateDatas(MigrateQueryDto queryDto){
        TableRefDto tableRef = resourcesInit.getTableRefDto();
        Map<String,Object> context = new HashMap<>();
        context.put("product_category.categoryType",queryDto.getCategoryType());
        context.put("migrateDao",migrateDao);
        context.put("tableRef",tableRef);
        Map<String, List<RowDataDto>> tablesData = migrateLogicService.getTablesData(context);
        logger.info(tablesData.toString());
    }

    public MigrateDao getMigrateDao() {
        return migrateDao;
    }

    public void setMigrateDao(MigrateDao migrateDao) {
        this.migrateDao = migrateDao;
    }
}

