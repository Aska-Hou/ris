package edu.wku.ris.common.pojo.exception;

import edu.wku.ris.common.pojo.response.ResponseEnum;
import org.springframework.util.Assert;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/3/29 12:00
 */
public class MyAssert {

    public static void notNull(Object obj, ResponseEnum responseEnum){
        if (obj == null)
            throw new BusinessException(responseEnum);
    }

    public static void isNull(Object obj, ResponseEnum responseEnum){
        if (obj != null)
            throw new BusinessException(responseEnum);
    }

    public static void isTrue(boolean result, ResponseEnum responseEnum){
        if (!result)
            throw new BusinessException(responseEnum);
    }


    public static void isEqual(String str1, String str2, ResponseEnum responseEnum){
        Assert.notNull(str1);
        Assert.notNull(str2);
        if (!str1.equals(str2))
            throw new BusinessException(responseEnum);
    }

    public static void notEqual(String str1, String str2, ResponseEnum responseEnum){
        Assert.notNull(str1);
        Assert.notNull(str2);
        if (str1.equals(str2))
            throw new BusinessException(responseEnum);
    }
}
