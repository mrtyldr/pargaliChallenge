package li.parga.pargalichallenge.dataAccess.abstracts;

import li.parga.pargalichallenge.entities.concretes.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet,Integer> {
    Wallet findByWalletId(int walletId);

    Wallet findByUser_Email(String email);

    Wallet deleteByUser_Email(String email);
}