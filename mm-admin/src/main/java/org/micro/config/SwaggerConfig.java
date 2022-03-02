package org.micro.config;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author micro-paul
 * @date 2022年01月27日 11:28
 */
@Configuration
@Data
public class SwaggerConfig {

    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    @Value("${swagger.enable}")
    private Boolean enable;

    /**
     * 项目应用名
     */
    @Value("${swagger.application-name}")
    private String applicationName;

    /**
     * 项目版本信息
     */
    @Value("${swagger.application-version}")
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    @Value("${swagger.application-description}")
    private String applicationDescription;

    @Bean
    public Docket createRestApi() {

        RequestParameterBuilder ticketPar = new RequestParameterBuilder();
        List<RequestParameter> pars = new ArrayList<>();
        ticketPar.description("token").name(tokenHeader)
                .in(ParameterType.HEADER).required(true)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.OAS_30)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationDescription)
                .contact(new Contact("micro-paul", "https://micro-paul.github.io/", "455870098@qq.com"))
                .version(applicationVersion)
                .build();
    }
}
