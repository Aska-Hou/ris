package edu.wku.ris.core.controller;

import edu.wku.ris.common.pojo.response.ResponseVO;
import edu.wku.ris.core.pojo.dto.GeneralIdDTO;
import edu.wku.ris.core.service.ModelIdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/27 11:03
 */
@RestController
@RequestMapping("/id")
@Api(tags = "统一id接口")
public class GeneralIdController {

}
