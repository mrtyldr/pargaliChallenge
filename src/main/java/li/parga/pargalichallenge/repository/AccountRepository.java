package li.parga.pargalichallenge.repository;

import li.parga.pargalichallenge.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountId(int walletId);

    Optional<List<Account>> findByUser_UserId(String userId);

    Optional<List<Account>> findByUser_Email(String email);


}
