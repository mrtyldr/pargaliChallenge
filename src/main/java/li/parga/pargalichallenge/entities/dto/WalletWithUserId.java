package li.parga.pargalichallenge.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletWithUserId {

    private double balance;

    private String accountType;

    private String currency;

    public WalletWithUserId(double balance) {
        this.balance = balance;
    }
}
