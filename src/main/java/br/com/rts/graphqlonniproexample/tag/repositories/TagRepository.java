package br.com.rts.graphqlonniproexample.tag.repositories;

import br.com.rts.graphqlonniproexample.tag.domains.Tag;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends ListCrudRepository<Tag, Integer> {
}
