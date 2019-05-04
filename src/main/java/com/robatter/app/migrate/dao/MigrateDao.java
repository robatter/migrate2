package com.robatter.app.migrate.dao;

import com.robatter.app.migrate.dto.TableColumnDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MigrateDao {

    Logger logger = LoggerFactory.getLogger(MigrateDao.class);
    JdbcTemplate jdbcTemplate ;

    public DatabaseMetaData getDatabaseMetaData(){
        try {
            DatabaseMetaData dbmd = jdbcTemplate.getDataSource().getConnection().getMetaData();
            return dbmd;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public Map<String,Map<String,TableColumnDto>> tablesMetaData(){
        DatabaseMetaData dbmd = getDatabaseMetaData();
        String[] types = { "TABLE" };
        Map<String,Map<String,TableColumnDto>> tablesMap = new HashMap<>();
        try {
            ResultSet rs = dbmd.getTables(null, null, null, types);
            while(rs.next()){
                String tableName = rs.getString("TABLE_NAME");
                Map<String,TableColumnDto> colsMap = new HashMap<>();
                ResultSet colsRs = dbmd.getColumns(null, null, tableName, "%");
                while(colsRs.next()){
                    TableColumnDto columnDto = new TableColumnDto();
                    String columnName = colsRs.getString("COLUMN_NAME").toLowerCase();
                    columnDto.setName(columnName);
                    int dataType = colsRs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
                    String dataTypeName = colsRs.getString("TYPE_NAME");//java.sql.Types类型   名称
                    columnDto.setType(dataType);
                    columnDto.setTypeName(dataTypeName);
                    colsMap.put(columnName, columnDto);
                }
                tablesMap.put(tableName,colsMap);
            }
            return tablesMap;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Map<String,Object>> selectData(String select){
        List<Map<String,Object>> list = jdbcTemplate.queryForList(select);
        return list;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
