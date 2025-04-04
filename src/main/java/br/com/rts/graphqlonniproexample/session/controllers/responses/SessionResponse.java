package br.com.rts.graphqlonniproexample.session.controllers.responses;

import br.com.rts.graphqlonniproexample.session.domains.Level;
import br.com.rts.graphqlonniproexample.tag.repositories.responses.TagResponse;

import java.util.Set;

public record SessionResponse(Integer id,
                              String title,
                              String description,
                              Level level,
                              Set<TagResponse> tags) {
}
