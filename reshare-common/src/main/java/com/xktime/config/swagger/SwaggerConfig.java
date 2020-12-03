package com.xktime.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xktime.apis"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("reshare API")
                        .description("应用程序API")
                        .version("0.0.1-SNAPSHOT")
                        .contact(new Contact("炫酷小子", "https://github.com/xktime/reshare", "luoliwen0913@foxmail.com"))
                        .license("Apache Licence 2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .build());
    }
}
