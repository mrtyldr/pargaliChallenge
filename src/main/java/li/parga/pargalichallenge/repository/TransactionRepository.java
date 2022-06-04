package li.parga.pargalichallenge.repository;

import li.parga.pargalichallenge.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    /*@Query("SELECT t FROM Transaction t WHERE fts(:category) = true")
    List<Transaction> search(@Param("category") String category);*/


}
