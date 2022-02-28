package app.debit.service;

import app.debit.models.DebitNew;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDebitNewService {

    Flux<DebitNew> findAll();
    Mono<DebitNew> findById(String id);
    Mono<DebitNew> findByCardNumber(String cardNumber);
    Mono<DebitNew> createDebit(DebitNew debitNew);
}
