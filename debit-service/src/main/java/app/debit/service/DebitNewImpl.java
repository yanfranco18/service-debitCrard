package app.debit.service;

import app.debit.models.DebitNew;
import app.debit.repository.IDebitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DebitNewImpl implements IDebitNewService{

    private final IDebitRepository debitRepository;

    private final AccountWebClient accountWebClient;

    @Override
    public Flux<DebitNew> findAll() {
        return debitRepository.findAll();
    }

    @Override
    public Mono<DebitNew> findById(String id) {
        return debitRepository.findById(id);
    }

    @Override
    public Mono<DebitNew> findByCardNumber(String cardNumber) {
        return debitRepository.findByCardNumber(cardNumber);
    }

    @Override
    public Mono<DebitNew> createDebit(DebitNew debitNew) {
        return accountWebClient.findByAccountNumber(debitNew.getNumberAccount())
                .flatMap(a -> {
                    DebitNew debitDto = new DebitNew();
                    debitDto.setCardNumber(debitNew.getCardNumber());
                    debitDto.setIdClient(a.getIdClient());
                    debitDto.setNumberAccount(a.getNumber());
                    debitDto.setCreationDate(LocalDate.now());
                    debitDto.setExpirationDate(LocalDate.from(LocalDate.now()).plusYears(4));
                    return debitRepository.save(debitDto);
                });
    }

}
