package edu.wku.ris.core.service;

import edu.wku.ris.core.pojo.bo.DoctorLogInBO;
import edu.wku.ris.core.pojo.bo.StudentLogInBO;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.pojo.entity.CoreStudent;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/19 13:50
 */
public interface LogInService {

    DoctorLogInBO doctorLogIn(CoreDoctor coreDoctor);

    void managerLogIn();

    void doctorLogOut(String token);

    StudentLogInBO studentLogIn(CoreStudent coreStudent);

    void studentLogOut(String token);
}
