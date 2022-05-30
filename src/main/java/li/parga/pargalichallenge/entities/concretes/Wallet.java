package li.parga.pargalichallenge.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;
import java.util.List;
import javax.persistence.*;

@Data
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

    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;

    public Wallet(User user,double balance) {
        this.user = user;
        this.balance = balance;
    }
}
