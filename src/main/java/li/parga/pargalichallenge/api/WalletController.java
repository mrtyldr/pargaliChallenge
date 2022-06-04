package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class WalletController {
    private final WalletService walletService;
    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    /*@PostMapping("/create-wallet")
    DataResult<Wallet> createWallet(@RequestBody WalletWithUserId walletWithUserId){
        return this.walletService.createWallet(walletWithUserId);
    }*/



}
