package li.parga.pargalichallenge.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutPasswordDto {


    private int userId;


    private String firstName;


    private String lastName;


    private String email;




}
