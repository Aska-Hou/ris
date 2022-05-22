package edu.wku.ris.core.controller.auth;

import edu.wku.ris.common.pojo.exception.MyAssert;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.common.pojo.response.ResponseVO;
import edu.wku.ris.core.pojo.bo.DoctorLogInBO;
import edu.wku.ris.core.pojo.bo.StudentLogInBO;
import edu.wku.ris.core.pojo.dto.DoctorLogInDTO;
import edu.wku.ris.core.pojo.dto.DoctorLogOutDTO;
import edu.wku.ris.core.pojo.dto.StudentLogInDTO;
import edu.wku.ris.core.pojo.dto.StudentLogOutDTO;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.pojo.entity.CoreStudent;
import edu.wku.ris.core.service.LogInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/19 13:27
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "登录统一接口")
@CrossOrigin
@Slf4j
public class LogInController {

    @Autowired
    public LogInService logInService;


    @PostMapping("/doctor/login")
    @ApiOperation(value = "PHD登录接口")
    public ResponseVO doctorLogIn(@RequestBody @ApiParam(name = "输入PHD账号密码登录") DoctorLogInDTO doctorAccountDTO){
        MyAssert.notNull(doctorAccountDTO.getEmail(), ResponseEnum.EMAIL_NULL_ERROR);
        MyAssert.notNull(doctorAccountDTO.getPassword(), ResponseEnum.PASSWORD_NULL_ERROR);

        // dto -> entity
        CoreDoctor coreDoctor = new CoreDoctor();
        coreDoctor.setEmail(doctorAccountDTO.getEmail());
        coreDoctor.setPassword(doctorAccountDTO.getPassword());

        DoctorLogInBO doctorLogInBO = logInService.doctorLogIn(coreDoctor);
        return ResponseVO.success().data("doctor", doctorLogInBO);
    }

    @PutMapping("/doctor/logout")
    @ApiOperation(value = "PHD登出接口")
    public ResponseVO doctorLogOut(@RequestBody @ApiParam(name = "传token退出登录") DoctorLogOutDTO doctorLogOutDTO){
        MyAssert.notNull(doctorLogOutDTO, ResponseEnum.TOKEN_INVALID_ERROR);
        logInService.doctorLogOut(doctorLogOutDTO.getToken());
        return ResponseVO.success();
    }

    @PostMapping("/student/login")
    @ApiOperation(value = "学生登录接口")
    public ResponseVO studentLogIn(@RequestBody @ApiParam(name = "输入学生账号密码登录") StudentLogInDTO studentLogInDTO){
        MyAssert.notNull(studentLogInDTO.getEmail(), ResponseEnum.EMAIL_NULL_ERROR);
        MyAssert.notNull(studentLogInDTO.getPassword(), ResponseEnum.PASSWORD_NULL_ERROR);

        // dto -> entity
        CoreStudent coreStudent = new CoreStudent();
        coreStudent.setEmail(studentLogInDTO.getEmail());
        coreStudent.setPassword(studentLogInDTO.getPassword());

        StudentLogInBO studentLogInBO = logInService.studentLogIn(coreStudent);
        return ResponseVO.success().data("student", studentLogInBO);
    }

    @PutMapping("/student/logout")
    @ApiOperation(value = "学生登出接口")
    public ResponseVO studentLogOut(@RequestBody @ApiParam(name = "传token退出登录") StudentLogOutDTO studentLogOutDTO){
        MyAssert.notNull(studentLogOutDTO, ResponseEnum.TOKEN_INVALID_ERROR);
        logInService.studentLogOut(studentLogOutDTO.getToken());
        return ResponseVO.success();
    }

}
