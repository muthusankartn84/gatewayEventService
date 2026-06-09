package com.example.gatewayservice.service;

import com.example.gatewayservice.Dto.EventRequest;
import com.example.gatewayservice.Entity.Event;
import com.example.gatewayservice.Feignclient.AccountFeignClient;
import com.example.gatewayservice.Repository.EventRepository;
import com.example.gatewayservice.mapper.EventMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class Eventservice {
    private final EventRepository eventRepository;
    private final AccountFeignClient accountFeignClient;

    @Autowired
    public Eventservice(final EventRepository eventRepository,final AccountFeignClient accountFeignClient) {
        this.eventRepository=eventRepository;
        this.accountFeignClient=accountFeignClient;
    }

    @Transactional
    @CircuitBreaker(
            name = "AccountService",
            fallbackMethod = "fallback")
    public Event create(EventRequest request) {

        Optional<Event> existing =
                eventRepository.findByEventId(
                        request.getEventId());

        if (existing.isPresent()) {
            return existing.get();
        }
        Event saved = eventRepository.save(EventMapper.map(request));
        accountFeignClient.applyTransaction(request.getAccountId(), EventMapper.map(saved));
        return saved;
    }

    public Event getEvent(String eventId) {
        return eventRepository.findByEventId(eventId)
                .orElseThrow();
    }

    public List<Event> getEvents(String accountId) {

        return eventRepository
                .findByAccountIdOrderByEventTimestampAsc(
                        accountId);
    }
    public Event fallback(
            EventRequest request,
            Exception ex) {

        throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Account Service unavailable");
    }
}


