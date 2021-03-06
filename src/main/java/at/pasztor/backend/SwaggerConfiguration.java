package at.pasztor.backend;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(
                PathSelectors
                    .any()
            )
            .build()
            .tags(
                new Tag(
                    "Blog posts",
                    "Create, modify, delete and list blog posts"
                )
            )
            .pathMapping("/")
            .apiInfo(metadata())
            .forCodeGeneration(true)
            .useDefaultResponseMessages(false)
            ;
    }

    private static ApiInfo metadata() {
        return new ApiInfoBuilder()
            .title("pasztor.at API")
            .description(
                "This API exposes the pasztor.at functionality."
            )
            .version("1")
            .build();
    }
}