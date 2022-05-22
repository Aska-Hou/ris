package edu.wku.ris.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wku.ris.core.mapper.CoreDoctorMapper;
import edu.wku.ris.core.mapper.LetterMapper;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.pojo.entity.RecommendationLetterForm;
import edu.wku.ris.core.service.RecommendationLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/3 12:23
 */
@Service
public class RecommendationLetterServiceImpl extends ServiceImpl<LetterMapper, RecommendationLetterForm> implements RecommendationLetterService {

    @Override
    public void submitLetterForm(RecommendationLetterForm form) {
        baseMapper.insert(form);
    }

    public String returnHello(){
        return "Hello";
    }

}
