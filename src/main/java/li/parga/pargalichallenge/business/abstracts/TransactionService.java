package li.parga.pargalichallenge.business.abstracts;

import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Transaction;
import li.parga.pargalichallenge.entities.concretes.dto.TransactionWithWalletsId;

import java.util.List;

public interface TransactionService {


    DataResult<TransactionWithWalletsId> makeTransaction(TransactionWithWalletsId transactionWithWalletsId);

    List<Transaction> findAll();

    DataResult<List<Transaction>> search(String description);

    DataResult<List<Transaction>> findAllOrderByDateAsc();

}
