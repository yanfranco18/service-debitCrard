package app.debit.repository;

import app.debit.models.DebitNew;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IDebitRepository extends ReactiveMongoRepository<DebitNew, String> {
    Mono<DebitNew> findByCardNumber(String cardNumber);
}
