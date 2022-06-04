package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.business.abstracts.UserService;
import li.parga.pargalichallenge.business.abstracts.WalletService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.ErrorDataResult;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
import li.parga.pargalichallenge.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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


    @PostMapping("/api/users")
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


    @DeleteMapping("/api/users/email")
    public DataResult<User> deleteUserByEmail(String email) {
        return this.userService.deleteUserByEmail(email);
    }

    @GetMapping("/api/users/{userId}/balance")
    public DataResult<WalletWithUserNameDto> findBalance(@PathVariable int userId) {
        return userService.findBalance(userId);
    }

    @GetMapping("/api/wallets")
    public DataResult<List<Wallet>> getWallet(Principal principal) {
        return this.walletService.findByUser_Email(principal.getName());
    }

    @PostMapping("api/users/{userId}/wallets")
    public DataResult<Wallet> addWallet(@PathVariable("userId") WalletWithUserId wallet) {
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
