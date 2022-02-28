package app.debit.service;

import app.debit.models.Account;
import reactor.core.publisher.Mono;

public interface IAccountWebClient {
    Mono<Account> findByAccountNumber(String number);
}
