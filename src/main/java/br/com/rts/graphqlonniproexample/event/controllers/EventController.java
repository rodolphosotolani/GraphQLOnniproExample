package br.com.rts.graphqlonniproexample.event.controllers;

import br.com.rts.graphqlonniproexample.event.domains.Event;
import br.com.rts.graphqlonniproexample.event.repositories.EventRepository;
import br.com.rts.graphqlonniproexample.session.domains.Session;
import br.com.rts.graphqlonniproexample.session.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventRepository repository;

    private final SessionRepository sessionRepository;

    @QueryMapping
    List<Event> events(){
        return repository.findAll();
    }

    @QueryMapping
    Optional<Event> event(@Argument Integer id){
        return repository.findById(id);
    }

    @QueryMapping
    List<Event> eventByName(@Argument String name){
        return repository.findAllByNameContains(name);
    }

    @SchemaMapping
    Window<Session> sessions(Event event, ScrollSubrange subrange){
        ScrollPosition scrollPosition = subrange.position().orElse(ScrollPosition.offset());
        Limit limit = Limit.of(subrange.count().orElse(10));
        Sort sort = Sort.by("title").ascending();
        return sessionRepository.findByEventId(event.getId(), scrollPosition, limit, sort);
    }
}
