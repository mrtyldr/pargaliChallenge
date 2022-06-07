package li.parga.pargalichallenge.service;

import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.entities.dto.TransactionWithAccountId;
import li.parga.pargalichallenge.repository.CategoryRepository;
import li.parga.pargalichallenge.repository.TransactionRepository;
import li.parga.pargalichallenge.repository.AccountRepository;
import li.parga.pargalichallenge.entities.Transaction;
import li.parga.pargalichallenge.entities.Account;

import li.parga.pargalichallenge.exceptions.NegativeBalanceException;
import li.parga.pargalichallenge.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;


    public DataResult<TransactionWithAccountId> makeTransaction(TransactionWithAccountId transactionWithAccountId) {
        Account account = this.accountRepository.findByAccountId(transactionWithAccountId.getAccountId())
                .orElseThrow(() -> new NotFoundException("account not found!"));

        if (account.getBalance() + transactionWithAccountId.getAmount() < 0) {
            throw new NegativeBalanceException("You don't have enough money for carrying this transaction out");
        }
        if (categoryRepository.findByCategoryId(transactionWithAccountId.getCategoryId()) == null)
            throw new NotFoundException("category doesn't exist please create a category before continue");
        account.setBalance(account.getBalance() + transactionWithAccountId.getAmount());
        /*Transaction transaction = new Transaction(transactionWithAccountId.getAmount(), transactionWithAccountId.getDate(), account,
                this.categoryRepository.findByCategoryId(transactionWithAccountId.getCategoryId()));*/
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Transaction transaction = modelMapper.map(transactionWithAccountId,Transaction.class);
        transactionRepository.save(transaction);
        accountRepository.save(account);

        return new SuccessDataResult<>(transactionWithAccountId, "transaction has successfully been terminated");
    }


    public List<Transaction> findAll() {
        if (this.transactionRepository.findAll().size() == 0)
            throw new NotFoundException("There aren't any transactions to be shown");
        return this.transactionRepository.findAll();
    }


    public DataResult<List<Transaction>> findAllOrderByDateAsc() {
        return new SuccessDataResult<>(this.transactionRepository.findAll(Sort.by("date").ascending()));
    }


}
