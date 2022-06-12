package li.parga.pargalichallenge.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@JsonIgnoreProperties({"user","transactions"})
@Indexed
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_type")
    @Field
    private String accountType;

    @Column(name ="currency")
    @Field
    private String currency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    @ContainedIn
    private List<Transaction> transactions;

    public Account(User user, double balance, String accountType, String currency) {
        this.balance = balance;
        this.accountType = accountType;
        this.currency = currency;
        this.user = user;
    }

}
