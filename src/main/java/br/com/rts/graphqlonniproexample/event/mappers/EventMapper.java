package br.com.rts.graphqlonniproexample.event.mappers;

import br.com.rts.graphqlonniproexample.event.controllers.requests.EventRequest;
import br.com.rts.graphqlonniproexample.event.controllers.responses.EventResponse;
import br.com.rts.graphqlonniproexample.event.domains.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring") // Integração com Spring
public interface EventMapper {

    // Mapeia de Event para EventResponse
    EventResponse entityToResponse(Event event);

    List<EventResponse> entityToResponse(List<Event> event);

    // Mapeia de EventRequest para Event (ignora o ID se for um novo evento)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessions", ignore = true) // Ignora sessions no mapeamento básico
    Event toEntity(EventRequest eventRequest);

    // Atualiza uma entidade existente a partir de um Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    void updateEntity(EventRequest eventRequest, @MappingTarget Event event);
}