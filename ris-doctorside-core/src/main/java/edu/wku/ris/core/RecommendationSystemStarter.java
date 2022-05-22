package edu.wku.ris.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/12 19:49
 */
@SpringBootApplication(scanBasePackages = "edu.wku.ris")
@ServletComponentScan(basePackages = "edu.wku.ris")
public class RecommendationSystemStarter {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationSystemStarter.class, args);
    }

}
