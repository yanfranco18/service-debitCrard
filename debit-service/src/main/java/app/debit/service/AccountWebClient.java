package app.debit.service;

import app.debit.config.properties.AppConfig;
import app.debit.models.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AccountWebClient implements IAccountWebClient{

    private final WebClient webClient;
    private final AppConfig properties;

    public AccountWebClient(AppConfig properties) {
        this.properties = properties;
        this.webClient = WebClient.create(properties.getUrl());
    }

    @Override
    public Mono<Account> findByAccountNumber(String number) {
        log.info("Obteniendo the number account");
        return webClient
                .get().uri(properties.getPath().concat("/search/").concat(number))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Account.class);
    }
}
