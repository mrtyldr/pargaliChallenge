package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.ErrorDataResult;
import li.parga.pargalichallenge.entities.User;
import li.parga.pargalichallenge.entities.Wallet;
import li.parga.pargalichallenge.entities.dto.UserWithoutWalletDto;

import li.parga.pargalichallenge.entities.dto.WalletWithUserId;
import li.parga.pargalichallenge.entities.dto.WalletWithUserNameDto;
import li.parga.pargalichallenge.service.UserService;
import li.parga.pargalichallenge.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final WalletService walletService;


    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public DataResult<Object> addUser(@RequestBody @Valid UserWithoutWalletDto user) {
        return this.userService.addUser(user);
    }

    @GetMapping("/api/user")
    @ResponseStatus(code = HttpStatus.OK)
    DataResult<User> findByUserId(Principal principal) {
        var result = userService.findByEmail(principal.getName());
        /*if (result.getData() == null)
            throw new NotFoundException();*/

        return result;
    }


    @DeleteMapping("/api/user")
    public DataResult<User> deleteUserByEmail(Principal principal) {
        return this.userService.deleteUserByEmail(principal.getName());
    }

    @GetMapping("/api/user/balance")
    public DataResult<WalletWithUserNameDto> findBalance(Principal principal) {
        return userService.findBalance(principal.getName());
    }

    @GetMapping("/api/wallets")
    public DataResult<List<Wallet>> getWallet(Principal principal) {
        return this.walletService.findByUser_Email(principal.getName());
    }

    @PostMapping("api/wallets")
    public DataResult<Wallet> addWallet(@RequestBody WalletWithUserId walletWithUserId, Principal principal) {
        User user = this.userService.findByEmail(principal.getName()).getData();
        Wallet wallet = new Wallet(this.userService.findByUserId( user.getUserId()).getData(),walletWithUserId.getBalance(), walletWithUserId.getAccountType(),
                walletWithUserId.getCurrency());
        return this.walletService.createWallet(wallet);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleNotValidExceptions(MethodArgumentNotValidException ex){
        Map <String,String>validationErrors = new HashMap<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorDataResult<Object>(validationErrors,"Validation Errors"),HttpStatus.BAD_REQUEST);
    }


}
