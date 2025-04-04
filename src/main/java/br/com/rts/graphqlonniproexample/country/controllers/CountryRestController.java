package br.com.rts.graphqlonniproexample.country.controllers;

import br.com.rts.graphqlonniproexample.country.domains.Country;
import br.com.rts.graphqlonniproexample.country.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryRestController {

    private final CountryService service;

    @GetMapping
    public Mono<List<Country>> getAllCountries() {
        return service.getAllCountries();
    }

}
