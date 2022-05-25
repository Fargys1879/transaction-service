package ru.fargys1879.transactions.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.fargys1879.transactions.dto.HttpResponse;
import ru.fargys1879.transactions.dto.TransactionDto;
import ru.fargys1879.transactions.mapper.TransactionMapper;
import ru.fargys1879.transactions.model.Transaction;
import ru.fargys1879.transactions.repository.TransactionRepository;


import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import static net.logstash.logback.marker.Markers.append;

@RestController
@Slf4j
public class RequestsCounterController {

    private final AtomicLong counter = new AtomicLong();
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;

    @Autowired
    public RequestsCounterController(TransactionRepository transactionRepository, TransactionMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @GetMapping("/requests")
    public Long getRequestsCount() {
        long result = counter.incrementAndGet();
        log.info(append("Request", result), "Request counter incremented");
        return result;
    }

    @GetMapping("/transactions")
    public Page<Transaction> getTransactions(@RequestParam Long accountId) {
        if (accountId != null) return new PageImpl<>(transactionRepository.getByAccountId(accountId));
        return new PageImpl<>(transactionRepository.findAll(Sort.sort(Transaction.class)));
    }

    @PostMapping("/createTransaction")
    public HttpResponse<Object> createTransaction(@RequestBody TransactionDto dto) {
        final Transaction transaction = mapper.mapTransactionDto(dto);
        transactionRepository.save(transaction);
        return HttpResponse.builder()
            .status(HttpStatus.CREATED)
            .responseCode(201)
            .body(transaction)
            .uri(URI.create("/createTransaction"))
            .build();
    }
}
