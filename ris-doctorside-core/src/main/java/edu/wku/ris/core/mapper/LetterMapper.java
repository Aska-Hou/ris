package edu.wku.ris.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wku.ris.core.pojo.entity.CoreStudent;
import edu.wku.ris.core.pojo.entity.RecommendationLetterForm;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/3 12:24
 */
public interface LetterMapper  extends BaseMapper<RecommendationLetterForm> {

    public void insertNewLetter(RecommendationLetterForm recommendationLetterForm);
}
