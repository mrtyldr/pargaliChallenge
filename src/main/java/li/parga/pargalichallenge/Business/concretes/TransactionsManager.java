package li.parga.pargalichallenge.Business.concretes;

import li.parga.pargalichallenge.Business.abstracts.TransactionService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataAccess.abstracts.CategoryDao;
import li.parga.pargalichallenge.dataAccess.abstracts.TransactionsDao;
import li.parga.pargalichallenge.dataAccess.abstracts.WalletDao;
import li.parga.pargalichallenge.entities.concretes.Transaction;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.TransactionWithWalletsId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsManager implements TransactionService {
    private final TransactionsDao transactionsDao;
    private final WalletDao walletDao;
    private final CategoryDao categoryDao;

    @Autowired
    public TransactionsManager(TransactionsDao transactionsDao, WalletDao walletDao, CategoryDao categoryDao) {
        this.transactionsDao = transactionsDao;
        this.walletDao = walletDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public DataResult<TransactionWithWalletsId> makeTransaction(TransactionWithWalletsId transactionWithWalletsId) {
        Wallet wallet = this.walletDao.findByWalletId(transactionWithWalletsId.getWalletId());
        wallet.setBalance(wallet.getBalance() + transactionWithWalletsId.getAmount());
        Transaction transaction =  new Transaction(transactionWithWalletsId.getAmount(), transactionWithWalletsId.getDate(),wallet,
                this.categoryDao.findByCategoryId(transactionWithWalletsId.getCategoryId()));
        transactionsDao.save(transaction);
        walletDao.save(wallet);

        return new SuccessDataResult<>(transactionWithWalletsId,"transaction has successfully been terminated");
    }

    @Override
    public List<Transaction> findAll() {
        return this.transactionsDao.findAll();
    }

    @Override
    public DataResult<List<Transaction> >search(String description) {
        return new SuccessDataResult<>(this.transactionsDao.search(description));
    }

    public DataResult<List<Transaction>> findAllOrderByDateAsc() {
        return new SuccessDataResult<>(this.transactionsDao.findAll(Sort.by("date").ascending()));
    }


}
