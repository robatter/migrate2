package com.robatter.app.migrate.dto;

import java.util.Map;

public class RowDataDto {
    TableRefDto tableRef;
    Map<String,Object> data;
    public RowDataDto(TableRefDto tableRef, Map<String,Object> data){
        this.tableRef = tableRef;
        this.data = data;
    }

   public String getInsert(){
        return null;
    }

    public String getDelete(){
        return null;
    }
}
