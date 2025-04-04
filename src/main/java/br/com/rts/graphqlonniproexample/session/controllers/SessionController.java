package br.com.rts.graphqlonniproexample.session.controllers;

import br.com.rts.graphqlonniproexample.session.domains.Session;
import br.com.rts.graphqlonniproexample.session.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SessionController {

    private final SessionRepository repository;

    @QueryMapping
    List<Session> sessions(){
        return repository.findAll();
    }

    @QueryMapping
    Optional<Session> session(@Argument Integer id){
        return repository.findById(id);
    }
}
