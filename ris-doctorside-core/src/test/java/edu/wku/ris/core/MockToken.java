package edu.wku.ris.core;

import edu.wku.ris.core.pojo.bo.DoctorLogInBO;
import edu.wku.ris.core.service.impl.LogInServiceImpl;
import edu.wku.ris.core.utils.RedisKeyUtils;
import edu.wku.ris.core.utils.RedisTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/26 20:54
 */
@SpringBootTest
public class MockToken {

    @Test
    public void test() {
        String accountTokenKey = RedisKeyUtils.getAccountTokenKey("1234");
        RedisTemplateUtils.storeObjectInCache(accountTokenKey, new DoctorLogInBO(), -1l);
    }

    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    @Test
//    @LTransactional(rollbackFor = Exception.class)
    public void test1() {
        RedisTemplateUtils.storeObjectInCache("k1", "k1", -1l);
//        redisTemplate.execute(new SessionCallback<Object>() {
//            @Override
//            public Object execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
//                operations.opsForValue().set("test", "test");
//                return operations.exec();
//            }
//        });
//        int i = 1 / 1;
    }

    @Autowired
    public LogInServiceImpl logInServiceImpl;

    @Test
    public void testTrans() {
//        logInServiceImpl.testTransaction();
    }


}
