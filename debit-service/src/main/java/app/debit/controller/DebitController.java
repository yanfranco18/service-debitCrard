package app.debit.controller;

import app.debit.models.DebitNew;
import app.debit.service.DebitNewImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/debits")
public class DebitController {

    private final DebitNewImpl debitservice;

    @CircuitBreaker(name="debit", fallbackMethod = "fallback")
    @TimeLimiter(name="debit")
    @GetMapping
    public Mono<ResponseEntity<Flux<DebitNew>>> getDebit(){
        log.info("iniciando lista");
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(debitservice.findAll()));
    }

    @CircuitBreaker(name="debit", fallbackMethod = "fallback")
    @TimeLimiter(name="debit")
    @GetMapping("/getById/{id}")
    public Mono<ResponseEntity<DebitNew>> getById(@PathVariable String id){
        return debitservice.findById(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CircuitBreaker(name="debit", fallbackMethod = "fallback")
    @TimeLimiter(name="debit")
    @PostMapping
    public Mono<ResponseEntity<DebitNew>>  saveDebit(@RequestBody DebitNew debitNew){
        return debitservice.createDebit(debitNew)
                .map(p -> ResponseEntity.created(URI.create("/debits/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CircuitBreaker(name="debit", fallbackMethod = "fallback")
    @TimeLimiter(name="debit")
    @GetMapping("/search/{number}")
    public Mono<ResponseEntity<DebitNew>> search(@PathVariable String number){
        //buscamos el tipo de number
        return debitservice.findByCardNumber(number)
                //mostramos la respuesta
                .map(p -> ResponseEntity.ok()
                        //Modificamos la respuesta en el body con el contentType
                        .contentType(MediaType.APPLICATION_JSON)
                        //devolvemos el objeto obtenido
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //metodo para manejar el error
    private String fallback(HttpServerErrorException ex) {
        return "Response 200, fallback method for error:  " + ex.getMessage();
    }

}
