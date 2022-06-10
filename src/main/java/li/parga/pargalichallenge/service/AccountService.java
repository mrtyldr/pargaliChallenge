package li.parga.pargalichallenge.service;

import li.parga.pargalichallenge.entities.User;
import li.parga.pargalichallenge.exceptions.NotFoundException;
import li.parga.pargalichallenge.repository.AccountRepository;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.repository.UserRepository;
import li.parga.pargalichallenge.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    public DataResult<Account> createAccount(Account account) {

        return new SuccessDataResult<>(this.accountRepository.save(account));
    }


    public DataResult<Account> findByUser_UserId(int userId) {
        return new SuccessDataResult<>(this.accountRepository.findByUser_UserId(userId));
    }


    public DataResult<List<Account>> findByUser_Email(String email) {
        var accounts = this.accountRepository.findByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("You don't have anny accounts to be shown."));
        return new SuccessDataResult<>(accounts);
    }

    public DataResult<Object> deleteAccount(int accountId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("user not found!"));

        Account account = this.accountRepository.findByAccountId(accountId)
                .filter(a -> a.getUser().getUserId().equals(user.getUserId()))
                .orElseThrow(() -> new NotFoundException("You don't have an account that has account id: " + accountId));
        this.accountRepository.delete(account);

        return new SuccessDataResult<>("account with id:" + accountId + " has succesfully been deleted.");
    }

    public DataResult<List<Account>> findAll(){
        return new SuccessDataResult<>(this.accountRepository.findAll());
    }
}
