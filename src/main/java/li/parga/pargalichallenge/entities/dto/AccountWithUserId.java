package li.parga.pargalichallenge.entities.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
