package com.robatter.app.migrate.dto;

import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Data
public class TableRefDto {
    String id;
    String namespace;
    String filter;
    String backIgnore;
    String virtual;
    String output;
    String valid;
    Map<String, TableColumnDto> colsMap;

    List<TableRefDto> children;
}
