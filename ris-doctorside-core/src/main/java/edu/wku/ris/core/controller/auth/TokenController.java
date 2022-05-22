package edu.wku.ris.core.controller.auth;

import edu.wku.ris.common.pojo.exception.MyAssert;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.common.pojo.response.ResponseVO;
import edu.wku.ris.core.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/19 22:41
 */
@RestController
@RequestMapping("/token")
@Api(tags = "Token管理接口")
@CrossOrigin
@Slf4j
public class TokenController {

    @Autowired
    @Qualifier("accountTokenServiceImpl")
    public TokenService accountTokenService;

    @GetMapping("/refreshToken/{token}")
    @ApiOperation("Token 刷新 返回永远success")
    public ResponseVO refreshToken(@PathVariable(value = "token", required = false) @ApiParam(name = "需要刷新的Token") String token){
        MyAssert.notNull(token, ResponseEnum.TOKEN_INVALID_ERROR);
        accountTokenService.refreshToken(token);
        return ResponseVO.success();
    }

    @GetMapping("/checkToken/{token}")
    @ApiOperation("Token有效性查询接口")
    public ResponseVO checkToken(@PathVariable(required = false) @ApiParam(name = "需要查询有效性的token") String token){
        MyAssert.notNull(token, ResponseEnum.TOKEN_INVALID_ERROR);
        boolean result = accountTokenService.checkToken(token);
        return result? ResponseVO.success() : ResponseVO.error(ResponseEnum.TOKEN_INVALID_ERROR.getMessage());
    }

}
