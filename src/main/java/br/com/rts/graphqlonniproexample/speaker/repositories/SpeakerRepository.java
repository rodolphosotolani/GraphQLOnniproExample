package br.com.rts.graphqlonniproexample.speaker.repositories;

import br.com.rts.graphqlonniproexample.speaker.domains.Speaker;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends ListCrudRepository<Speaker, Integer> {
}
