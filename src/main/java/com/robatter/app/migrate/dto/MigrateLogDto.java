package com.robatter.app.migrate.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class MigrateLogDto {

    int id;
    MigrateType migrateType;
    MigrateStatus migrateStatus;
    int selectedCost;
    int insertedCost;
    int deletedCost;
    String remark;

    public static enum MigrateType{
        MIGRATE(1),
        BACK(2);
        private final int value;

        private MigrateType(int value) {
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }

    public static enum MigrateStatus{
        SELECTED(1),
        SELECT_FAIL(2),
        INSERTED(3),
        INSERT_FAIL(4),
        DELETED(5),
        DELETE_FAIL(6);
        private final int value;

        private MigrateStatus(int value) {
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }
}
