package li.parga.pargalichallenge.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"transactions"})
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @NotNull
    @Column(name = "passwrd")
    private String password;

    @Email
    @NotNull
    @NotBlank
    @Column(name = "email")
    private String email;

   /* @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;*/

    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;

    @Column(name = "role")
    private String role = "USER";

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
    public User(int id, String firstName, String lastName, String password, String email) {
        this(firstName, lastName, password, email);
        this.userId = id;
    }
}
