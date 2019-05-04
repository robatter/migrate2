package com.robatter.app.migrate.controller;

import com.robatter.app.migrate.api.ApiResponse;
import com.robatter.app.migrate.dto.MigrateQueryDto;
import com.robatter.app.migrate.service.MigrateProcessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/v1/rest")
@RestController
public class MigrateController {

    @Resource(name="curMigrateService")
    MigrateProcessService curService;

    @Resource(name="hisMigrateService")
    MigrateProcessService hisService;

    @RequestMapping(value="/migrate", method= RequestMethod.POST)
    @ApiOperation(value="migrate", notes="migrate data")
    public ApiResponse migrate(MigrateQueryDto queryDto){
        ApiResponse<String> ar = new ApiResponse<>();
        curService.migrateDatas(queryDto);
        return ar;
    }
}
