package app.debit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Debits")
public class DebitNew {

    @Id
    private String id;

    private String cardNumber;

    private String idClient;

    private String numberAccount;

    private LocalDate creationDate;

    private LocalDate expirationDate;
}
