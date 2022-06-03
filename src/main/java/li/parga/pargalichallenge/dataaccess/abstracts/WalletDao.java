package li.parga.pargalichallenge.dataaccess.abstracts;

import li.parga.pargalichallenge.entities.concretes.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet,Integer> {
    Wallet findByWalletId(int walletId);

    Wallet findByUser_UserId(int userId);

    Wallet findByUser_Email(String email);


}
