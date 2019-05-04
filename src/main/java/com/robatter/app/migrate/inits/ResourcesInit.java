package com.robatter.app.migrate.inits;

import com.robatter.app.migrate.dao.MigrateDao;
import com.robatter.app.migrate.dto.TableColumnDto;
import com.robatter.app.migrate.dto.TableRefDto;
import com.robatter.app.migrate.service.MigrateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.util.Map;


public class ResourcesInit {

    Logger logger = LoggerFactory.getLogger(ResourcesInit.class);

    String tableRefPath;
    TableRefDto tableRefDto;
    @Resource(name="curMigrateDao")
    MigrateDao curMigrateDao;

    public void init(){
        ClassPathResource resource = new ClassPathResource(tableRefPath);
        try {
            Map<String, Map<String, TableColumnDto>> tablesMap = curMigrateDao.tablesMetaData();
            tableRefDto = MigrateHelper.parse(resource.getInputStream(),tablesMap);
        }catch (Exception e){
            logger.error("resource init {}",new Object[]{e.getMessage()});
        }
    }

    public TableRefDto getTableRefDto(){
        return tableRefDto;
    }

    public String getTableRefPath() {
        return tableRefPath;
    }

    public void setTableRefPath(String tableRefPath) {
        this.tableRefPath = tableRefPath;
    }
}
