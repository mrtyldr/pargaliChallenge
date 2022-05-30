package li.parga.pargalichallenge.Business.abstracts;

import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;

public interface WalletService {
    DataResult<Wallet> createWallet(WalletWithUserId walletWithUserId);

    DataResult<Wallet> findByUser_Email(String email);


}