package edu.wku.ris.core.service;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/22 0:12
 */
public interface TokenService {

    boolean checkToken(String token);

    void refreshToken(String token);
}
