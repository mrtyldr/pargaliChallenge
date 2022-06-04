package li.parga.pargalichallenge.business.concretes;

import li.parga.pargalichallenge.business.abstracts.WalletService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataaccess.abstracts.UserDao;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class WalletManager implements WalletService {
    private li.parga.pargalichallenge.dataaccess.abstracts.WalletDao walletDao;
    private final UserDao userDao;

    public WalletManager(li.parga.pargalichallenge.dataaccess.abstracts.WalletDao walletDao, UserDao userDao) {
        this.walletDao = walletDao;
        this.userDao = userDao;
    }

    @Override
    public DataResult<Wallet> createWallet(Wallet wallet) {

        return new SuccessDataResult<>(this.walletDao.save(wallet));
    }

    @Override
    public DataResult<Wallet> findByUser_UserId(int userId) {
        return new SuccessDataResult<>(this.walletDao.findByUser_UserId(userId));
    }

    @Override
    public DataResult<List<Wallet>> findByUser_Email(String email) {
        return new SuccessDataResult<>(this.walletDao.findByUser_Email(email));
    }
}
