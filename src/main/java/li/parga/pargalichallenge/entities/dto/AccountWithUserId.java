package li.parga.pargalichallenge.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class AccountWithUserId {
    @NotBlank
    @NotNull
    private double balance;

    @NotNull
    @NotBlank
    private String accountType;

    @NotNull
    @NotBlank
    private String currency;

    public AccountWithUserId(double balance) {
        this.balance = balance;
    }
}
