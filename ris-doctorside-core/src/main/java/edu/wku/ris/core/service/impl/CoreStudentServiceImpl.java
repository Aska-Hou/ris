package edu.wku.ris.core.service.impl;

import edu.wku.ris.core.mapper.CoreDoctorMapper;
import edu.wku.ris.core.mapper.CoreStudentMapper;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.pojo.entity.CoreStudent;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wku.ris.core.service.CoreStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aska
 * @since 2022-04-12
 */
@Service
public class CoreStudentServiceImpl extends ServiceImpl<CoreStudentMapper, CoreStudent> implements CoreStudentService {

    @Autowired
    public CoreStudentMapper studentMapper;

    @Override
    public List<CoreStudent> getStudentAccountByEmailAndPassword(CoreStudent coreStudent) {
        return studentMapper.selectStudentByEmailAndPassword(coreStudent);
    }
}
