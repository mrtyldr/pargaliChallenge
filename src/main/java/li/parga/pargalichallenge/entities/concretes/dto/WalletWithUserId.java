package li.parga.pargalichallenge.entities.concretes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletWithUserId {
    private int userId;
    private double balance;

    private String accountType;

    private String currency;


}
