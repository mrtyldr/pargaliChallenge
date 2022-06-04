package li.parga.pargalichallenge.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountWithUserId {

    private double balance;

    private String accountType;

    private String currency;

    public AccountWithUserId(double balance) {
        this.balance = balance;
    }
}
