package li.parga.pargalichallenge.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"category"})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction(double amount, Date date, Account account, Category category) {
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.category = category;
    }
}
