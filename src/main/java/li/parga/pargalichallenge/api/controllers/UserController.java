package li.parga.pargalichallenge.api.controllers;


import li.parga.pargalichallenge.Business.abstracts.UserService;
import li.parga.pargalichallenge.Business.abstracts.WalletService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {
    private final UserService userService;
    private final WalletService walletService;
    @Autowired
    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @PostMapping("/api/users")
    public DataResult<User> addUser(@RequestBody UserWithoutWalletDto user){
        return this.userService.addUser(user);
    }

    @GetMapping("/api/users/{userId}")
    DataResult<User> findByUserId(@PathVariable int userId){
        return this.userService.findByUserId(userId);
    }

    @GetMapping("/api/users/email")
    public DataResult<User> findByEmail( String email){
        return this.userService.findByEmail(email);
    }

    @DeleteMapping("/api/users/email")
    public DataResult<User> deleteUserByEmail( String email){
        return this.userService.deleteUserByEmail(email);
    }

    @GetMapping("/api/users/{userId}/balance")
    public DataResult<WalletWithUserNameDto> findBalance(@PathVariable int userId){
        return this.userService.findBalance(userId);
    }

    @GetMapping("/api/users/{userId}/wallets")
    public DataResult<Wallet> getWallet(@PathVariable int userId){
        return this.walletService.findByUser_UserId(userId);
    }

    @PostMapping("api/users/{userId}/wallets")
    public DataResult<Wallet> addWallet(@PathVariable("userId") WalletWithUserId wallet){
        return this.walletService.createWallet(wallet);
    }
}
