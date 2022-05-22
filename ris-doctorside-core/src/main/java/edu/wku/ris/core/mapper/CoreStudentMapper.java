package edu.wku.ris.core.mapper;

import edu.wku.ris.core.config.datasource.DataSourceEnum;
import edu.wku.ris.core.config.datasource.DataSourceSwitcher;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wku.ris.core.pojo.entity.CoreStudent;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aska
 * @since 2022-04-12
 */
public interface CoreStudentMapper extends BaseMapper<CoreStudent> {

    @DataSourceSwitcher(value = DataSourceEnum.SLAVE)
    public List<CoreStudent> selectStudentByEmailAndPassword(CoreStudent coreStudent);


}
