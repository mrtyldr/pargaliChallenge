package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.business.abstracts.UserService;
import li.parga.pargalichallenge.business.abstracts.WalletService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/api/user")
    DataResult<User> findByUserId(Principal principal){
        var result = userService.findByEmail(principal.getName());
        if (result.getData() == null)
            throw new NotFoundException("user not found!");

        return result;
    }


    @DeleteMapping("/api/users/email")
    public DataResult<User> deleteUserByEmail( String email){
        return this.userService.deleteUserByEmail(email);
    }

    @GetMapping("/api/users/{userId}/balance")
    public DataResult<WalletWithUserNameDto> findBalance(@PathVariable int userId){
        return userService.findBalance(userId);
    }

    @GetMapping("/api/wallets")
    public DataResult<List<Wallet>> getWallet(Principal principal){
        return this.walletService.findByUser_Email(principal.getName());
    }

    @PostMapping("api/users/{userId}/wallets")
    public DataResult<Wallet> addWallet(@PathVariable("userId") WalletWithUserId wallet){
        return this.walletService.createWallet(wallet);
    }
}
