package li.parga.pargalichallenge.entities.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithUserNameDto {
    private String firstName;
    private String lastName;
    private double balance;
}
