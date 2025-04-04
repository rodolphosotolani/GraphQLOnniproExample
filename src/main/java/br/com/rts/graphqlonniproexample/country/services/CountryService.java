package br.com.rts.graphqlonniproexample.country.services;

import br.com.rts.graphqlonniproexample.country.domains.Country;
import lombok.extern.log4j.Log4j2;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@Service
public class CountryService {

    private final HttpGraphQlClient graphQlClient;

    public CountryService() {
        WebClient client = WebClient.builder().baseUrl("https://countries.trevorblades.com").build();
        graphQlClient = HttpGraphQlClient.builder(client).build();
    }


    public Mono<List<Country>> getAllCountries() {
        //language=GraphQL
        String document = """
                query{
                    countries{
                        name
                        emoji
                        currency
                        code
                        capital
                        awsRegion
                    }
                }
                """;

        return graphQlClient
                .document(document)
                .retrieve("countries")
                .toEntityList(Country.class);
    }
}
