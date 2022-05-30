package li.parga.pargalichallenge.entities.concretes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithoutWalletDto {
    private int userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

}
