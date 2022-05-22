package edu.wku.ris.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/18 18:38
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiConfig() {
        System.out.println("Hello");
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("API Document - Recommendation Information System")
                .description("Recommendation Information API Document")
                .description("除登录、Token服务以外，所有接口均需在header传有效的Token \"token\"=\"***\"")
                .contact(new Contact("Aska", "", "houyueran@126.com"))
                .version("1.0")
                .build();
    }
}
