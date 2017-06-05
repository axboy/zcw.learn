package local.zcw.demo.conf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.async.DeferredResult
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import sun.util.resources.LocaleData

import static com.google.common.base.Predicates.or
import static springfox.documentation.builders.PathSelectors.regex

/**
 * Created by zcw on 2017/06/05.
 */
@Configuration
@EnableSwagger2
class SwaggerConf {

    @Bean
    Docket testApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)

        docket.apiInfo(new ApiInfoBuilder()
                .title("测试Swagger-UI")
                .description("Swagger-UI demo")
                .version("v1.0")
                .termsOfServiceUrl("https://www.baidu.com/")
                .contact(new Contact("zcw", "https://github.com/zengchw", "zcw1994@live.com"))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build()
        )
        docket.directModelSubstitute(LocaleData, String)
                .ignoredParameterTypes(MetaClass)       //groovy需要，java不用
                .groupName("test")
                .genericModelSubstitutes(DeferredResult)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(regex("/api/.*")))

        return docket
    }
}
