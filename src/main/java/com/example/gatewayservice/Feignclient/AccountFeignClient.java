package com.example.gatewayservice.Feignclient;

import com.example.gatewayservice.sharedDto.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient(
        name = "ACCOUNT-SERVICE",
        path = "/accounts")
public interface AccountFeignClient {
    @PostMapping("/{accountId}/transactions")
    void applyTransaction(
            @PathVariable String accountId,
            @RequestBody TransactionRequest request);

    @GetMapping("/{accountId}/balance")
    BigDecimal getBalance(
            @PathVariable String accountId);
}
