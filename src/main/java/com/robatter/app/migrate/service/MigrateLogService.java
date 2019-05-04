package com.robatter.app.migrate.service;

import com.robatter.app.migrate.dao.MigrateLogDao;
import com.robatter.app.migrate.dto.MigrateLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MigrateLogService {

    @Autowired
    MigrateLogDao migrateLogDao;

    public int saveMigrateLog(MigrateLogDto logDto){
        int count = migrateLogDao.saveMigrateLog(logDto);
        return count;
    }
}
