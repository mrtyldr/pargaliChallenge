package li.parga.pargalichallenge.api.controllers;

import li.parga.pargalichallenge.Business.abstracts.WalletService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controllers/walletcontroller")
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

    @GetMapping("/findbyemail/{email}")
    public DataResult<Wallet> findByUser_Email(@PathVariable String email){
        return this.walletService.findByUser_Email(email);
    }

}