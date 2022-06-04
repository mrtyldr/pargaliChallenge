package li.parga.pargalichallenge.service;

import li.parga.pargalichallenge.repository.AccountRepository;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.repository.UserRepository;
import li.parga.pargalichallenge.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    public DataResult<Account> createWallet(Account account) {

        return new SuccessDataResult<>(this.accountRepository.save(account));
    }


    public DataResult<Account> findByUser_UserId(int userId) {
        return new SuccessDataResult<>(this.accountRepository.findByUser_UserId(userId));
    }


    public DataResult<List<Account>> findByUser_Email(String email) {
        return new SuccessDataResult<>(this.accountRepository.findByUser_Email(email));
    }
}
