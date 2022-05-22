package edu.wku.ris.core.service;

import edu.wku.ris.core.pojo.entity.CoreDoctor;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.wku.ris.core.pojo.entity.CoreStudent;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aska
 * @since 2022-04-12
 */
public interface CoreStudentService extends IService<CoreStudent> {

    List<CoreStudent> getStudentAccountByEmailAndPassword(CoreStudent coreStudent);

}
