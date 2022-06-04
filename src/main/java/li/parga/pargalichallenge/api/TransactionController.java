package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.business.abstracts.CategoryService;
import li.parga.pargalichallenge.business.abstracts.TransactionService;
import li.parga.pargalichallenge.business.abstracts.UserService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Transaction;
import li.parga.pargalichallenge.entities.concretes.dto.TransactionWithWalletsId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final CategoryService categoryService;


    @PostMapping("/api/transactions")
    public DataResult<TransactionWithWalletsId> makeTransaction(@RequestBody TransactionWithWalletsId transactionWithWalletsId) {
        return this.transactionService.makeTransaction(transactionWithWalletsId);
    }

    @GetMapping("/api/transactions")
    List<Transaction> findAll() {
        return this.transactionService.findAll();
    }

    @GetMapping("/api/search")
    public DataResult<List<Transaction>> search(String description) {
        return this.transactionService.search(description);
    }

    @GetMapping("/api/transactions/asc")
    public DataResult<List<Transaction>> findAllOrderByDateAsc() {
        return this.transactionService.findAllOrderByDateAsc();
    }


}
