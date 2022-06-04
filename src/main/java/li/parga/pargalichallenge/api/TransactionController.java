package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.Transaction;

import li.parga.pargalichallenge.entities.dto.TransactionWithWalletsId;
import li.parga.pargalichallenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping("/api/transaction")
    public DataResult<TransactionWithWalletsId> makeTransaction(@RequestBody TransactionWithWalletsId transactionWithWalletsId) {
        return this.transactionService.makeTransaction(transactionWithWalletsId);
    }

    @GetMapping("/api/transactions")
    List<Transaction> findAll() {
        return this.transactionService.findAll();
    }



    @GetMapping("/api/transactions/asc")
    public DataResult<List<Transaction>> findAllOrderByDateAsc() {
        return this.transactionService.findAllOrderByDateAsc();
    }


}
