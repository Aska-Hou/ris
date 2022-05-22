package edu.wku.ris.core.service;

import edu.wku.ris.core.config.datasource.DataSourceEnum;
import edu.wku.ris.core.config.datasource.DataSourceSwitcher;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aska
 * @since 2022-04-19
 */
public interface CoreDoctorService extends IService<CoreDoctor> {

    List<CoreDoctor> getDoctorAccountByEmailAndPassword(CoreDoctor coreDoctor);

}
