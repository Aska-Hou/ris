package edu.wku.ris.core.service;

import edu.wku.ris.common.pojo.exception.BusinessException;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.core.pojo.bo.DoctorLogInBO;
import edu.wku.ris.core.pojo.entity.CoreDoctor;
import edu.wku.ris.core.service.impl.LogInServiceImpl;
import edu.wku.ris.core.utils.RedisKeyUtils;
import edu.wku.ris.core.utils.RedisTemplateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/3 21:49
 */
@SpringBootTest
public class LogInServiceImplTest {

    @InjectMocks
    public LogInServiceImpl logInService;

    @Mock
    public CoreDoctorService coreDoctorService;

    public final String token = "token";

    @Test
    public void testDoctorLogIn(){
        CoreDoctor coreDoctor = new CoreDoctor();
        Mockito.when(coreDoctorService.getDoctorAccountByEmailAndPassword(coreDoctor))
                .thenReturn(Collections.singletonList(coreDoctor));

        MockedStatic<RedisKeyUtils> redisKeyUtilsMockedStatic = Mockito.mockStatic(RedisKeyUtils.class);
        redisKeyUtilsMockedStatic.when(() -> RedisKeyUtils.getAccountTokenKey(token))
                .thenReturn("account:" + token);

        MockedStatic<RedisTemplateUtils> redisTemplateUtilsMockedStatic = Mockito.mockStatic(RedisTemplateUtils.class);
        DoctorLogInBO doctorLogInBO = logInService.doctorLogIn(coreDoctor);
        Assertions.assertNotNull(doctorLogInBO);

        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.storeObjectInCache(any(), any(), any())).thenThrow(new RuntimeException());
        try {
            logInService.doctorLogIn(coreDoctor);
        } catch (BusinessException businessException){
            Assertions.assertEquals(businessException.getMessage(), ResponseEnum.REDIS_NOT_AVAILABLE.getMessage());
        }

        redisTemplateUtilsMockedStatic.close();
        redisKeyUtilsMockedStatic.close();
    }


    @Test
    public void testDoctorLogOut(){
        MockedStatic<RedisKeyUtils> redisKeyUtilsMockedStatic = Mockito.mockStatic(RedisKeyUtils.class);
        redisKeyUtilsMockedStatic.when(() -> RedisKeyUtils.getAccountTokenKey(token))
                .thenReturn("account:" + token);

        MockedStatic<RedisTemplateUtils> redisTemplateUtilsMockedStatic = Mockito.mockStatic(RedisTemplateUtils.class);
        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.getObjectInCache(RedisKeyUtils.getAccountTokenKey(token)))
                        .thenReturn(new DoctorLogInBO());

        logInService.doctorLogOut(token);

        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.getObjectInCache(RedisKeyUtils.getAccountTokenKey(token)))
                .thenThrow(new RuntimeException());
        try {
            logInService.doctorLogOut(token);
        } catch (BusinessException businessException){
            Assertions.assertEquals(businessException.getMessage(), ResponseEnum.REDIS_NOT_AVAILABLE.getMessage());
        }

        redisTemplateUtilsMockedStatic.close();
        redisKeyUtilsMockedStatic.close();

    }
}
