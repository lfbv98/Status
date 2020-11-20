package com.axity.status.config.swagger;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
   
   @Value("${swagger.host}")
   private String host;



   @Value("${swagger.base.path}")
   private String basePath;



   @Bean
   public Docket api(ServletContext servletContext) {
       return new Docket(DocumentationType.SWAGGER_2)
       .host(host)
       .pathProvider(new RelativePathProvider(servletContext) {
           @Override
           public String getApplicationBasePath() {
               return basePath;
           }
       })
       .select()
               .apis(RequestHandlerSelectors.basePackage("com.axity.status.controller"))
               .paths(PathSelectors.any()).build().apiInfo(apiInfo());
   }



   private ApiInfo apiInfo() {
       ApiInfo apiInfo = new ApiInfo("API Apartado de lugares de UDP ", "API para el apartado de lugares", "v.1.0", "",
               new Contact("", "", ""), "", "");
       return apiInfo;
   }    
}