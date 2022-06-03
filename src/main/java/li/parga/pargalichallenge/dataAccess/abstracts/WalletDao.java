package li.parga.pargalichallenge.dataAccess.abstracts;

import li.parga.pargalichallenge.entities.concretes.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface WalletDao extends JpaRepository<Wallet,Integer> {
    Wallet findByWalletId(int walletId);

    List<Wallet> findByUser_UserId(int userId);

    Wallet findByUser_Email(String email);


}
