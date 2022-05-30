package li.parga.pargalichallenge.api.controllers;


import li.parga.pargalichallenge.Business.abstracts.UserService;
import li.parga.pargalichallenge.Business.abstracts.WalletService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controllers/usercontroller")
public class UserController {
    private final UserService userService;
    private final WalletService walletService;
    @Autowired
    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @PostMapping("/adduser")
    public DataResult<User> addUser(@RequestBody UserWithoutWalletDto user){
        int userId = user.getUserId();
        return this.userService.addUser(user);
    }

    @GetMapping("/findbyuserid/{userId}")
    DataResult<User> findByUserId(@PathVariable int userId){
        return this.userService.findByUserId(userId);
    }

    @GetMapping("/findbyemail/{email}")
    public DataResult<User> findByEmail(@PathVariable String email){
        return this.userService.findByEmail(email);
    }

    @GetMapping("/deleteuser/{email}")
    public DataResult<User> deleteUserByEmail(@PathVariable String email){
        return this.userService.deleteUserByEmail(email);
    }

    @GetMapping("/findbalance")
    public DataResult<WalletWithUserNameDto> findBalance(String email){
        return this.userService.findBalance(email);
    }
}
