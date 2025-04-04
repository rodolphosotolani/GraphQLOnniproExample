package br.com.rts.graphqlonniproexample.event.controllers.requests;

import br.com.rts.graphqlonniproexample.session.controllers.responses.SessionResponse;

import java.time.LocalDate;
import java.util.Set;

public record EventRequest(String name,
                           String description,
                           LocalDate startDate,
                           LocalDate endDate,
                           LocalDate cfpStartDate,
                           LocalDate cfpEndDate,
                           String location,
                           String website,
                           Set<SessionResponse> sessions) {
}
