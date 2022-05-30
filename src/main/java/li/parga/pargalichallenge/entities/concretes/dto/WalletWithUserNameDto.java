package li.parga.pargalichallenge.entities.concretes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletWithUserNameDto {
    private String firstName;
    private String lastName;
    private double balance;
}
