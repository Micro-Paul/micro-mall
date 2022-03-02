package org.micro.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.micro.pojo.YxUserDO;
import org.micro.service.YxUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author micro-paul
 * @date 2022年01月27日 14:34
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/users")
public class YxUserController {

    private final YxUserService yxUserService;


    public YxUserController(YxUserService yxUserService) {
        this.yxUserService = yxUserService;
    }

    @ApiOperation("导出用户数据")
    @GetMapping(value = "/select")
    public ResponseEntity<Object> selectUsers(YxUserDO yxUserDO, Page page) {
        return new ResponseEntity<>(yxUserService.list(), HttpStatus.OK);
    }

}
