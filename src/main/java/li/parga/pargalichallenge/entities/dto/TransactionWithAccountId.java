package li.parga.pargalichallenge.entities.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionWithAccountId {
    private double amount;

    private Date date;

    private int WalletId;

    private int categoryId;


}
