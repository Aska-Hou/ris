package edu.wku.ris.core.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aska
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/coreAttachfile")
@Api(tags = "附件上传接口")
public class CoreAttachfileController {

    @GetMapping("/hello")
    @ApiOperation(value = "文件上传")
    public void hello(){
        
    }

}

