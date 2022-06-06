package li.parga.pargalichallenge.entities.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TransactionWithAccountId {
    private double amount;

    private Date date;

    private int accountId;

    private int categoryId;


}
