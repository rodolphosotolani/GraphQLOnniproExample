package br.com.rts.graphqlonniproexample.event.controllers;

import br.com.rts.graphqlonniproexample.event.controllers.requests.EventRequest;
import br.com.rts.graphqlonniproexample.event.controllers.responses.EventResponse;
import br.com.rts.graphqlonniproexample.event.domains.Event;
import br.com.rts.graphqlonniproexample.event.mappers.EventMapper;
import br.com.rts.graphqlonniproexample.event.repositories.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@Tag(name = "Eventos", description = "Operações relacionadas a eventos")
public class EventRestController {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    // Buscar todos os eventos
    @Operation(
            summary = "Listar todos os eventos",
            description = "Retorna uma lista paginada de eventos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Eventos encontrados",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Nenhum evento encontrado")
            })
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity
                .ok(eventMapper.entityToResponse(events));
    }

    // Buscar evento por ID
    @Operation(
            summary = "Buscar evento por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evento encontrado",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        return event
                .map(eventMapper::entityToResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar novo evento
    @Operation(
            summary = "Criar novo evento",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Evento criado",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody Event event) {
        Event savedEvent = eventRepository.save(event);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventMapper.entityToResponse(savedEvent));
    }

    // Atualizar evento existente
    @Operation(
            summary = "Atualizar evento existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evento atualizado",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
            })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Integer id,
            @RequestBody EventRequest eventRequest) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    eventMapper.updateEntity(eventRequest, existingEvent);
                    Event updatedEvent = eventRepository.save(existingEvent);
                    return ResponseEntity.ok(eventMapper.entityToResponse(updatedEvent));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar evento
    @Operation(
            summary = "Deletar evento",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Evento deletado"),
                    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
