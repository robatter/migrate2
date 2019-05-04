package com.robatter.app.migrate.service;

import com.robatter.app.migrate.dao.MigrateDao;
import com.robatter.app.migrate.dto.RowDataDto;
import com.robatter.app.migrate.dto.TableDto;
import com.robatter.app.migrate.dto.TableRefDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MigrateLogicService {

    public Map<String,List<RowDataDto>> getTablesData(Map<String,Object> context){
        TableRefDto tableRef = (TableRefDto) context.get("tableRef");
        Map<String,List<RowDataDto>> tables = new HashMap<>();
        handle(tableRef,context, tables);
        return tables;
    }

    void handle(TableRefDto tableRef, Map<String,Object> context, Map<String,List<RowDataDto>> tables){
        MigrateDao migrateDao = (MigrateDao)context.get("migrateDao");
        String tableName = tableRef.getId();
        String select = String.format("select * from %s where %s",tableName,convertFilter(tableRef.getNamespace(),tableRef.getFilter(),context));
        List<Map<String,Object>> list = migrateDao.selectData(select);
        List<RowDataDto> rowDataList = new ArrayList<>();
        for(Map<String,Object> data : list){
            rowDataList.add(new RowDataDto(tableRef, data));
            output2Context(tableRef,data,context);
            if(!CollectionUtils.isEmpty(tableRef.getChildren())){
                for(TableRefDto tableRefChild : tableRef.getChildren()){
                    handle(tableRefChild, context, tables);
                }
            }
        }
        tables.put(tableName, rowDataList);
    }

    String convertFilter(String namespace, String filter, Map<String,Object> context){
        Set<String> keys = MigrateHelper.pickUpKey(filter);
        for(String key : keys){
            String keyContext = namespace + "." + key;
            String value = MigrateHelper.loopGetParentKeyValue(keyContext,context);
            filter = filter.replace("${" + key + "}", value);
        }
        return filter;
    }

    void output2Context(TableRefDto tableRef, Map<String,Object> data, Map<String,Object> context){
        String output = tableRef.getOutput();
        Set<String> outputs = StringUtils.commaDelimitedListToSet(output);
        for(String op : outputs){
            String[] opa = op.split(":");
            String key = tableRef.getNamespace() + "." + MigrateHelper.unwrap(opa[1]);
            String value = MigrateHelper.convertObject2String(data.get(opa[0]));
            context.put(key,value);
        }
    }
}
