package br.com.rts.graphqlonniproexample.configurations;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
