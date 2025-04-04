package br.com.rts.graphqlonniproexample.event.controllers;

import br.com.rts.graphqlonniproexample.event.controllers.requests.EventRequest;
import br.com.rts.graphqlonniproexample.event.controllers.responses.EventResponse;
import br.com.rts.graphqlonniproexample.event.domains.Event;
import br.com.rts.graphqlonniproexample.event.mappers.EventMapper;
import br.com.rts.graphqlonniproexample.event.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventRestController {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    // Buscar todos os eventos
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity
                .ok(eventMapper.entityToResponse(events));
    }

    // Buscar evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        return event
                .map(eventMapper::entityToResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar novo evento
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    // Atualizar evento existente
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
