package com.tripmate.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info()
            .title("TripMATE API")
            .version(appVersion)
            .description("TripMATE 내부용 API 입니다.")
            .termsOfService("http://swagger.io/terms/")
            .contact(new Contact()
                         .name("tripmate manager")
                         .url("https://localhost/")
                         .email("tripmate-manager@gmail.com"))
            .license(new License()
                         .name("Apache License Version 2.0")
                         .url("http://www.apache.org/licenses/LICENSE-2.0")
            );

        return new OpenAPI()
            .components(new Components())
            .info(info);
    }
}