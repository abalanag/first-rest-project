package ro.siit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket scrumAllyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(path -> List.of(
                        "/packages/{packId}",
                        "/packages/title/{packTitle}",
                        "/packages",
                        "/packages/{date}/prices/calculate",
                        "/packages",
                        "/packages/{packId}").contains(path)).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("My first rest project")
                .description("This is an rest application that manage the packages")
                .license("MIT License")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }
}
