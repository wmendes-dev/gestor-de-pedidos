package br.com.pagamentos_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Estoque API").version("v1"));
    }

    @Bean
    public OpenApiCustomizer baseOpenApiCustomizer() {
        return openAPI -> {
            Components components = openAPI.getComponents();
            List<SecurityRequirement> requirements = new ArrayList<>();

            SecurityScheme bearerTokenSecurityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER);
            components.addSecuritySchemes("JWT Bearer Token", bearerTokenSecurityScheme);
            requirements.add(new SecurityRequirement().addList("JWT Bearer Token"));

            openAPI.security(requirements);
        };
    }

}