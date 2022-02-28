package app.debit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Account {

    private String id;
    private String number;
    private Double lineAvailable;
    private Double lineUsed;
    private Double balancePast;
    private Double amount;
    private Date createDate;
    private Integer countMovements;
    private String idClient;
    private String idProducts;
}
