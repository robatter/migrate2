package com.robatter.app.migrate.dao;

import com.robatter.app.migrate.dto.MigrateLogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class MigrateLogDao {
    Logger logger = LoggerFactory.getLogger(MigrateLogDao.class);
    @Resource(name="hisJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public int saveMigrateLog(final MigrateLogDto logDto){
        int count = jdbcTemplate.update("INSERT INTO migrate_log (migrate_type,migrate_status,selected_cost,inserted_cost,deleted_cost,create_time,update_time,remark) " +
                "values (?,?,?,?,?,?,?,?)", new PreparedStatementSetter(){
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, logDto.getMigrateType().value());
                        ps.setInt(2, logDto.getMigrateStatus().value());
                        ps.setInt(3, logDto.getSelectedCost());
                        ps.setInt(4, logDto.getInsertedCost());
                        ps.setInt(5,logDto.getDeletedCost());
                        ps.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
                        ps.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
                        ps.setString(8,logDto.getRemark());
                    }
                }
            );
        return count;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
