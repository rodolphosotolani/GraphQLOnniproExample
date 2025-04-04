package br.com.rts.graphqlonniproexample.session.repositories;

import br.com.rts.graphqlonniproexample.session.domains.Session;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ListCrudRepository<Session, Integer> {

    Window<Session> findByEventId(Integer eventId,
                                  ScrollPosition position,
                                  Limit limit,
                                  Sort sort);
}
