package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /*@PostMapping("/create-wallet")
    DataResult<Wallet> createWallet(@RequestBody WalletWithUserId walletWithUserId){
        return this.walletService.createWallet(walletWithUserId);
    }*/



}
