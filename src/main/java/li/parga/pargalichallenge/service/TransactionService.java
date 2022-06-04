package li.parga.pargalichallenge.service;

import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.entities.dto.TransactionWithWalletsId;
import li.parga.pargalichallenge.repository.CategoryRepository;
import li.parga.pargalichallenge.repository.TransactionRepository;
import li.parga.pargalichallenge.repository.WalletRepository;
import li.parga.pargalichallenge.entities.Transaction;
import li.parga.pargalichallenge.entities.Wallet;

import li.parga.pargalichallenge.exceptions.NegativeBalanceException;
import li.parga.pargalichallenge.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
    }


    public DataResult<TransactionWithWalletsId> makeTransaction(TransactionWithWalletsId transactionWithWalletsId) {
        Wallet wallet = this.walletRepository.findByWalletId(transactionWithWalletsId.getWalletId());
        if (wallet.getBalance() + transactionWithWalletsId.getAmount() < 0) {
            throw new NegativeBalanceException("You don't have enough money for carrying this transaction out");
        }
        if(categoryRepository.findByCategoryId(transactionWithWalletsId.getCategoryId())==null)
            throw new NotFoundException("category doesn't exist please create a category before continue");
        wallet.setBalance(wallet.getBalance() + transactionWithWalletsId.getAmount());
        Transaction transaction = new Transaction(transactionWithWalletsId.getAmount(), transactionWithWalletsId.getDate(), wallet,
                this.categoryRepository.findByCategoryId(transactionWithWalletsId.getCategoryId()));
        transactionRepository.save(transaction);
        walletRepository.save(wallet);

        return new SuccessDataResult<>(transactionWithWalletsId, "transaction has successfully been terminated");
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
