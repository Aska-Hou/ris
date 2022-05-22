package edu.wku.ris.core.service.impl;

import edu.wku.ris.common.pojo.exception.BusinessException;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.core.service.TokenService;
import edu.wku.ris.core.utils.RedisKeyUtils;
import edu.wku.ris.core.utils.RedisTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/22 0:12
 */
@Service
@Slf4j
public class AccountTokenServiceImpl implements TokenService {

    @Override
    public boolean checkToken(String token) {
        try {
            String accountTokenKey = RedisKeyUtils.getAccountTokenKey(token);
            Long expireTime = RedisTemplateUtils.getExpireTime(accountTokenKey);
            if (expireTime ==  -2 || expireTime == -1){
                return false;
            }
        }
        catch (Exception exception){
            log.error("检查Token异常 token:" + token, exception);
            throw new BusinessException(ResponseEnum.REDIS_NOT_AVAILABLE);
        }
        return true;
    }

    @Override
    public void refreshToken(String token) {
        try {
            String accountTokenKey = RedisKeyUtils.getAccountTokenKey(token);
            RedisTemplateUtils.expireObjectInCache(accountTokenKey, RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME);
        }
        catch (Exception exception){
            log.error("登录Token刷新异常 token:" + token, exception);
            throw new BusinessException(ResponseEnum.REDIS_NOT_AVAILABLE);
        }

    }
}
