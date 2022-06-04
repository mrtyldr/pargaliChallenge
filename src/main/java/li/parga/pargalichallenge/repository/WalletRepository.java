package li.parga.pargalichallenge.repository;

import li.parga.pargalichallenge.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByWalletId(int walletId);

    Wallet findByUser_UserId(int userId);

    List<Wallet> findByUser_Email(String email);


}
