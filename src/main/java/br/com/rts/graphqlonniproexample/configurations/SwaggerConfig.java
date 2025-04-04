package br.com.rts.graphqlonniproexample.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Eventos")
                        .version("1.0.0")
                        .description("API para gerenciamento de eventos")
                        .contact(new Contact()
                                .name("Equipe de Suporte R.T.S.")
                                .email("rt.sotolani@gmail.com")));
    }
}