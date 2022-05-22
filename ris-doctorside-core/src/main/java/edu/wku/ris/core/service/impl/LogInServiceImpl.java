package edu.wku.ris.core.service.impl;

import edu.wku.ris.common.pojo.exception.BusinessException;
import edu.wku.ris.common.pojo.exception.MyAssert;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.core.pojo.bo.DoctorLogInBO;
import edu.wku.ris.core.pojo.bo.StudentLogInBO;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.pojo.entity.CoreStudent;
import edu.wku.ris.core.service.CoreDoctorService;
import edu.wku.ris.core.service.CoreStudentService;
import edu.wku.ris.core.service.LogInService;
import edu.wku.ris.core.utils.RedisKeyUtils;
import edu.wku.ris.core.utils.RedisTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/19 13:54
 */
@Service
@Slf4j
public class LogInServiceImpl implements LogInService {

    @Autowired
    public CoreDoctorService coreDoctorService;


    @Override
    public DoctorLogInBO doctorLogIn(CoreDoctor coreDoctor) {
        List<CoreDoctor> doctorList = coreDoctorService.getDoctorAccountByEmailAndPassword(coreDoctor);
        MyAssert.isTrue(doctorList.size() >= 1, ResponseEnum.LOGIN_ERROR);
        CoreDoctor doctor = doctorList.get(0);
        DoctorLogInBO doctorLogInBO = new DoctorLogInBO(
                doctor.getId(), doctor.getEmail(), doctor.getName(),
                doctor.getLevel());
        String token = UUID.randomUUID().toString();
        // redis存储token
        String tokenKey = RedisKeyUtils.getAccountTokenKey(token);
        try {
            RedisTemplateUtils.storeObjectInCache(tokenKey, doctorLogInBO, RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME);
        } catch (Exception exception) {
            log.error("用户[" + doctorLogInBO.getId() + "]登陆失败,redis请求异常]" + exception);
            throw new BusinessException(ResponseEnum.REDIS_NOT_AVAILABLE);
        }
        doctorLogInBO.setToken(token);
        return doctorLogInBO;
    }

    @Override
    public void managerLogIn() {

    }

    @Override
    public void doctorLogOut(String token) {
        String tokenKey = RedisKeyUtils.getAccountTokenKey(token);
        try {
            DoctorLogInBO objectInCache = RedisTemplateUtils.getObjectInCache(tokenKey);
            if (objectInCache != null) {
                RedisTemplateUtils.removeObjectInCache(tokenKey);
            }
        } catch (Exception exception) {
            log.error("用户 token:[" + tokenKey + "]登出失败,redis请求异常]" + exception);
            throw new BusinessException(ResponseEnum.REDIS_NOT_AVAILABLE);
        }

    }


    @Autowired
    public CoreStudentService coreStudentService;


    @Override
    public StudentLogInBO studentLogIn(CoreStudent coreStudent) {
        List<CoreStudent> studentList = coreStudentService.getStudentAccountByEmailAndPassword(coreStudent);
        MyAssert.isTrue(studentList.size() >= 1, ResponseEnum.LOGIN_ERROR);
        CoreStudent student = studentList.get(0);
        StudentLogInBO studentLogInBO = new StudentLogInBO(
                student.getId(), student.getEmail(), student.getName(),
                student.getLevel());
        String token = UUID.randomUUID().toString();
        // redis存储token
        String tokenKey = RedisKeyUtils.getAccountTokenKey(token);
        try {
            RedisTemplateUtils.storeObjectInCache(tokenKey, studentLogInBO, RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME);
        } catch (Exception exception) {
            log.error("学生 [" + studentLogInBO.getId() + "]登陆失败,redis请求异常]" + exception);
            throw new BusinessException(ResponseEnum.REDIS_NOT_AVAILABLE);
        }
        studentLogInBO.setToken(token);
        return studentLogInBO;
    }

    @Override
    public void studentLogOut(String token) {
        String tokenKey = RedisKeyUtils.getAccountTokenKey(token);
        try {
            StudentLogInBO objectInCache = RedisTemplateUtils.getObjectInCache(tokenKey);
            if (objectInCache != null) {
                RedisTemplateUtils.removeObjectInCache(tokenKey);
            }
        } catch (Exception exception) {
            log.error("学生 token:[" + tokenKey + "]登出失败,redis请求异常]" + exception);
            throw new BusinessException(ResponseEnum.REDIS_NOT_AVAILABLE);
        }

    }
}
