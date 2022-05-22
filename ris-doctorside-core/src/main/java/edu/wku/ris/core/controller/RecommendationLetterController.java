package edu.wku.ris.core.controller;

import edu.wku.ris.common.pojo.response.ResponseVO;
import edu.wku.ris.core.pojo.entity.RecommendationLetterForm;
import edu.wku.ris.core.service.RecommendationLetterService;
import edu.wku.ris.core.service.impl.RecommendationLetterServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/3 12:21
 */
@RestController
@RequestMapping("/letter")
public class RecommendationLetterController {

    @Autowired
    public RecommendationLetterServiceImpl recommendationLetterService;

    @ApiOperation(value = "推荐信申请提交接口")
    @PostMapping("/submitLetter")
    public ResponseVO submitRecommendationLetterApplication(@RequestBody RecommendationLetterForm recommendationLetterForm){
        recommendationLetterService.submitLetterForm(recommendationLetterForm);
        return ResponseVO.success();
    }

    public String test(){
        return recommendationLetterService.returnHello();
    }
}
