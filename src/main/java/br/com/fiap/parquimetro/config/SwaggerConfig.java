package br.com.fiap.parquimetro.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public List<GroupedOpenApi> apis() {
        return Arrays.asList(
                GroupedOpenApi.builder()
                .group("motorista-api**")
                .pathsToMatch("/pessoas/**")
                .build(),
                GroupedOpenApi.builder()
                        .group("veiculos-api**")
                        .pathsToMatch("/carros/**")
                        .build(),
                GroupedOpenApi.builder()
                        .group("cartao-api**")
                        .pathsToMatch("/cartoes/**")
                        .build(),
                GroupedOpenApi.builder()
                        .group("estacionamento-api**")
                        .pathsToMatch("/estacionamentos/**")
                        .build()
        );
    }

}
