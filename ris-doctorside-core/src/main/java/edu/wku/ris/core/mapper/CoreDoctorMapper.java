package edu.wku.ris.core.mapper;

import edu.wku.ris.core.config.datasource.DataSourceEnum;
import edu.wku.ris.core.config.datasource.DataSourceSwitcher;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aska
 * @since 2022-04-19
 */
public interface CoreDoctorMapper extends BaseMapper<CoreDoctor> {

    @DataSourceSwitcher(value = DataSourceEnum.SLAVE)
    public List<CoreDoctor> selectDoctorByEmailAndPassword(CoreDoctor coreDoctor);

}
