package li.parga.pargalichallenge.service;

import li.parga.pargalichallenge.repository.WalletRepository;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.repository.UserRepository;
import li.parga.pargalichallenge.entities.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    private WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }


    public DataResult<Wallet> createWallet(Wallet wallet) {

        return new SuccessDataResult<>(this.walletRepository.save(wallet));
    }


    public DataResult<Wallet> findByUser_UserId(int userId) {
        return new SuccessDataResult<>(this.walletRepository.findByUser_UserId(userId));
    }


    public DataResult<List<Wallet>> findByUser_Email(String email) {
        return new SuccessDataResult<>(this.walletRepository.findByUser_Email(email));
    }
}
