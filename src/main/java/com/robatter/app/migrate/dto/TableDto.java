package com.robatter.app.migrate.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TableDto {
    TableRefDto tableRef;
    String name;
    String inserts;
    String deletes;


}
