package edu.wku.ris.core.service;

import edu.wku.ris.common.pojo.exception.BusinessException;
import edu.wku.ris.core.service.impl.AccountTokenServiceImpl;
import edu.wku.ris.core.utils.RedisKeyUtils;
import edu.wku.ris.core.utils.RedisTemplateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/3 19:43
 */
@SpringBootTest
public class AccountTokenServiceImplTest {

    @InjectMocks
    public AccountTokenServiceImpl accountTokenService;

    @BeforeEach
    public void openMock(){
        MockitoAnnotations.openMocks(this);
    }

    public String token = "token";

    @Test
    public void testCheckToken(){
        // Mock RedisKeyUtils
        MockedStatic<RedisKeyUtils> redisKeyUtilsMockedStatic = mockStatic(RedisKeyUtils.class);

        // Mock RedisTemplateUtils
        MockedStatic<RedisTemplateUtils> redisTemplateUtilsMockedStatic = mockStatic(RedisTemplateUtils.class);

        redisKeyUtilsMockedStatic.when(() -> RedisKeyUtils.getAccountTokenKey(token))
                .thenReturn("accountTokenKey:" + token);

        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.getExpireTime(RedisKeyUtils.getAccountTokenKey(token)))
                .thenReturn(-1l);
        Assertions.assertFalse(accountTokenService.checkToken(token));

        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.getExpireTime(RedisKeyUtils.getAccountTokenKey(token)))
                .thenThrow(new RuntimeException());
        try {
            accountTokenService.checkToken(token);
        } catch (BusinessException exception){

        }

        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.getExpireTime(RedisKeyUtils.getAccountTokenKey(token)))
                .thenReturn(anyLong());
        Assertions.assertTrue(accountTokenService.checkToken(token));

        redisKeyUtilsMockedStatic.close();
        redisTemplateUtilsMockedStatic.close();
    }

    @Test
    public void testRefreshToken(){
        // Mock RedisKeyUtils
        MockedStatic<RedisKeyUtils> redisKeyUtilsMockedStatic = mockStatic(RedisKeyUtils.class);

        // Mock RedisTemplateUtils
        MockedStatic<RedisTemplateUtils> redisTemplateUtilsMockedStatic = mockStatic(RedisTemplateUtils.class);

        redisKeyUtilsMockedStatic.when(() -> RedisKeyUtils.getAccountTokenKey(token)).thenReturn("accountToken:" + token);
//        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.expireObjectInCache(RedisKeyUtils.getAccountTokenKey(token), RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME));
        System.out.println(RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME);
        accountTokenService.refreshToken(token);

        redisTemplateUtilsMockedStatic.when(() -> RedisTemplateUtils.expireObjectInCache(RedisKeyUtils.getAccountTokenKey(token), RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME))
                .thenThrow(new RuntimeException());
        try {
            accountTokenService.refreshToken(token);
        } catch (BusinessException businessException){

        }

        redisKeyUtilsMockedStatic.close();
        redisTemplateUtilsMockedStatic.close();

    }


}
