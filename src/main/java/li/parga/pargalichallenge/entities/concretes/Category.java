package li.parga.pargalichallenge.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
@JsonIgnoreProperties("transactions")
public class Category {

    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
