package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.core.utilities.fulltextsearch.IndexingService;
import li.parga.pargalichallenge.core.utilities.fulltextsearch.SearchService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.Transaction;

import li.parga.pargalichallenge.entities.dto.TransactionWithAccountId;
import li.parga.pargalichallenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final SearchService searchService;

    private final IndexingService indexingService;

    @PostMapping("/api/transaction")
    public DataResult<TransactionWithAccountId> makeTransaction(@RequestBody TransactionWithAccountId transactionWithAccountId) {
        return this.transactionService.makeTransaction(transactionWithAccountId);
    }

    @GetMapping("/api/transactions")
    List<Transaction> findAll() {
        return this.transactionService.findAll();
    }


    @GetMapping("/api/transactions/asc")
    public DataResult<List<Transaction>> findAllOrderByDateAsc() {
        return this.transactionService.findAllOrderByDateAsc();
    }

    @GetMapping("/api/transactions/search")
    public List<Transaction> search(String accountType){
        return this.searchService.getTransactionBasedOnCategory(accountType);

    }

    @GetMapping("/api/indexingservice")
    public void setIndexingService() throws InterruptedException {
        this.indexingService.initiateIndexing();
    }


}
