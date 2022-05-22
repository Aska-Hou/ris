package edu.wku.ris.core.utils;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/21 23:16
 */
public class RedisKeyUtils {

    public static final String GENERAL_PREFIX = "ris_core";

    public static final String TOKEN = "token";

    public static final String ACCOUNT = "account";

    public static final Long GENERAL_TOKEN_EXPIRE_TIME = 15l;

    public static String getAccountTokenKey(String token){
        return GENERAL_PREFIX + ":" + ACCOUNT + ":" + TOKEN + ":" + token;
    }

    public static final void test(){

    }
}
