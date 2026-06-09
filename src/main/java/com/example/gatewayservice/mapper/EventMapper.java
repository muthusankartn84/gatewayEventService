package com.example.gatewayservice.mapper;

import com.example.gatewayservice.Dto.EventRequest;
import com.example.gatewayservice.Entity.Event;
import com.example.gatewayservice.sharedDto.TransactionRequest;

public class EventMapper {

    public static Event map (EventRequest eventRequest) {
        Event event = new Event();
        event.setType(eventRequest.getType());
        event.setAmount(eventRequest.getAmount());
        event.setEventId(eventRequest.getEventId());
        event.setAccountId(eventRequest.getAccountId());
        event.setEventTimestamp(eventRequest.getEventTimestamp());
        return event;
    }
    public static TransactionRequest map (Event event) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setType(event.getType());
        transactionRequest.setAmount(event.getAmount());
        transactionRequest.setEventId(event.getEventId());
        transactionRequest.setAccountId(event.getAccountId());
        transactionRequest.setEventTimestamp(event.getEventTimestamp());
        return transactionRequest;
    }
}
