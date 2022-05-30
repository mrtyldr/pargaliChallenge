package li.parga.pargalichallenge.entities.concretes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletWithUserId {
    private int userId;
    private double balance;

    public WalletWithUserId(double balance) {
        this.balance = balance;
    }
}
