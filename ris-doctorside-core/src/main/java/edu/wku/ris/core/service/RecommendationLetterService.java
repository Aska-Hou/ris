package edu.wku.ris.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.pojo.entity.RecommendationLetterForm;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/3 12:21
 */
public interface RecommendationLetterService extends IService<RecommendationLetterForm> {

    void submitLetterForm(RecommendationLetterForm form);

}
