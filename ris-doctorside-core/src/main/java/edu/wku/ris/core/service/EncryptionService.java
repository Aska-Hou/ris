package edu.wku.ris.core.service;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/19 16:00
 */
public interface EncryptionService {

    String encrypt(String password);

    String decrypt(String password);
}
