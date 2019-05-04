package com.robatter.app.migrate.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TableColumnDto {
    String name;
    int type;
    String typeName;
}
