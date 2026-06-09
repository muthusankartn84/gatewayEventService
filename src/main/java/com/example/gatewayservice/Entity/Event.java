package com.example.gatewayservice.Entity;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builderst
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;

    private String accountId;

    private String type;

    private BigDecimal amount;

    private String currency;

    private Instant eventTimestamp;
}
