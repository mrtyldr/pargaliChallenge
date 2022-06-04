package li.parga.pargalichallenge.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.mapping.Join;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
@JsonIgnoreProperties({"user","transactions"})
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private int walletId;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name ="currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet",cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Wallet(User user,double balance, String accountType, String currency) {
        this.balance = balance;
        this.accountType = accountType;
        this.currency = currency;
        this.user = user;
    }

}
