package li.parga.pargalichallenge.entities.dto;


import lombok.*;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionWithAccountId {
    private double amount;

    private Date date;

    private int accountId;

    private int categoryId;


}
