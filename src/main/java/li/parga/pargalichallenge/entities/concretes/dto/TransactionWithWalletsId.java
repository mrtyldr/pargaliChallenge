package li.parga.pargalichallenge.entities.concretes.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionWithWalletsId {
    private double amount;

    private Date date;

    private int WalletId;

    private int categoryId;

    private String currency;


}
