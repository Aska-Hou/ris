package edu.wku.ris.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/27 21:57
 */
public class ExecutorTest {

    @Test
    public void test1(){
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    }
}
