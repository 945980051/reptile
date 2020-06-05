package com.sunshine.reptile.config;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2019年11月15日 9:48
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: Swagger2配置
 * @date Date : 2019年11月15日 9:49
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket createMallApi() {
        return this.createDocket(SystemConstant.MALL_TITLE, SystemConstant.MALL_API_PACKAGE, createMallInfo());
    }

    private ApiInfo createMallInfo() {
        return new ApiInfoBuilder().title(SystemConstant.MALL_TITLE)
                .termsOfServiceUrl("")
                .contact(new Contact("health", "", ""))
                .version(SystemConstant.MALL_API_VERSIPN).build();
    }

    /**
     * @author zhengwenyao
     * @Description:创建Swagger2容器
     * @param @param
     *            groupName 分组名称
     * @param @param
     *            basePacjage 扫描包路径
     * @param @param
     *            apiInfo 接口资源描述信息
     * @param @return
     * @return Docket
     * @throws Exception
     */
    private Docket createDocket(String groupName, String basePackage, ApiInfo apiInfo) {
        List<Parameter> parameters = new ArrayList<Parameter>();
        this.createParameters(parameters,SystemConstant.PAGE_NUM, SystemConstant.PAGE_NUM, false, "Integer");
        this.createParameters(parameters,SystemConstant.PAGE_SIZE, SystemConstant.PAGE_SIZE, false, "Integer");
        this.createParameters(parameters,SystemConstant.ORDER_BY, SystemConstant.ORDER_BY, false, "String");
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.any()).build()
                .globalOperationParameters(parameters);
    }

    private void createParameters(List<Parameter> parameters,String name,String desc,Boolean required,String modeRef) {
        ParameterBuilder builder = new ParameterBuilder();
        builder.name(name).description(desc)
                .modelRef(new ModelRef(modeRef)).parameterType("header").required(required).build();
        parameters.add(builder.build());
    }
}
