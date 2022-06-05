package li.parga.pargalichallenge.repository;

import li.parga.pargalichallenge.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountId(int walletId);

    Account findByUser_UserId(int userId);

    List<Account> findByUser_Email(String email);


}
