package com.example.gatewayservice.Restcontroller;


import com.example.gatewayservice.Dto.EventRequest;
import com.example.gatewayservice.Entity.Event;
import com.example.gatewayservice.service.Eventservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class Eventcontroller {

        private final Eventservice eventService;

        @Autowired
        public Eventcontroller(final Eventservice eventService) {
            this.eventService= eventService;
        }

        @PostMapping
        public ResponseEntity<Event> create(
                @Valid @RequestBody EventRequest request) {

            return ResponseEntity.ok(
                    eventService.create(request));
        }

        @GetMapping("/{eventId}")
        public ResponseEntity<Event> getEvent(
                @PathVariable String eventId) {

            return ResponseEntity.ok(
                    eventService.getEvent(eventId));
        }

        @GetMapping
        public ResponseEntity<List<Event>> getEvents(
                @RequestParam String account) {

            return ResponseEntity.ok(
                    eventService.getEvents(account));
        }

        @GetMapping("/health")
        public ResponseEntity<String> health() {
            return ResponseEntity.ok("UP");
        }
}
