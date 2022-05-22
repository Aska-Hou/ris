package edu.wku.ris.core.service.impl;

import edu.wku.ris.core.config.datasource.DataSourceEnum;
import edu.wku.ris.core.config.datasource.DataSourceSwitcher;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.mapper.CoreDoctorMapper;
import edu.wku.ris.core.service.CoreDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aska
 * @since 2022-04-19
 */
@Service
public class CoreDoctorServiceImpl extends ServiceImpl<CoreDoctorMapper, CoreDoctor> implements CoreDoctorService {

    @Autowired
    public CoreDoctorMapper doctorMapper;

    @Override
    public List<CoreDoctor> getDoctorAccountByEmailAndPassword(CoreDoctor coreDoctor) {
        return doctorMapper.selectDoctorByEmailAndPassword(coreDoctor);
    }
}
