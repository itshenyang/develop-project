package com.sy.utils;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: shenyang
 * @Date: 2020/1/16 17:13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket sampleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(apiPaths())
                .build();
    }

    private Predicate<String> apiPaths() {
        return Predicates.or(
                PathSelectors.regex("/hello.*"),
                PathSelectors.regex("/swagger.*"),
                PathSelectors.regex("/student.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("itshenyang移动端API")
                .description("itshenyang移动端API")
                .version("1.0")
                .build();
    }
}
