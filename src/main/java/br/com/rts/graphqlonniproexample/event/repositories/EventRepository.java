package br.com.rts.graphqlonniproexample.event.repositories;

import br.com.rts.graphqlonniproexample.event.domains.Event;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ListCrudRepository<Event, Integer> {
}
