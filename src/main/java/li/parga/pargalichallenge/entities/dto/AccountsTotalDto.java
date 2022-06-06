package li.parga.pargalichallenge.entities.dto;

import li.parga.pargalichallenge.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountsTotalDto {
    private String firstName;

    private String lastName;

    private List<Account> accounts;

    private String total;
}
