package li.parga.pargalichallenge.api;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.ErrorDataResult;
import li.parga.pargalichallenge.entities.User;
import li.parga.pargalichallenge.entities.Account;
import li.parga.pargalichallenge.entities.dto.UserWithoutAccountDto;

import li.parga.pargalichallenge.entities.dto.AccountWithUserId;
import li.parga.pargalichallenge.entities.dto.AccountWithUserNameDto;
import li.parga.pargalichallenge.service.UserService;
import li.parga.pargalichallenge.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final UserService userService;
    @Autowired
    private final AccountService accountService;


    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public DataResult<Object> addUser(@RequestBody @Valid UserWithoutAccountDto user) {
        return this.userService.addUser(user);
    }

    @GetMapping("/api/user")
    @ResponseStatus(code = HttpStatus.OK)
    DataResult<User> findByUserId(Principal principal) {
        return userService.findByEmail(principal.getName());
    }


    @DeleteMapping("/api/user")
    public DataResult<User> deleteUserByEmail(Principal principal) {
        return this.userService.deleteUserByEmail(principal.getName());
    }

    @GetMapping("/api/user/balance")
    public DataResult<AccountWithUserNameDto> findBalance(Principal principal) {
        return userService.findBalance(principal.getName());
    }

    @GetMapping("/api/accounts")
    public DataResult<List<Account>> getAccounts(Principal principal) {
        return this.accountService.findByUser_Email(principal.getName());
    }

    @PostMapping("api/account")
    public DataResult<Account> addAccount(@RequestBody @Valid AccountWithUserId accountWithUserId, Principal principal) {
        User user = this.userService.findByEmail(principal.getName()).getData();
        Account account = new Account(this.userService.findByUserId(user.getUserId()).getData(), accountWithUserId.getBalance(), accountWithUserId.getAccountType(),
                accountWithUserId.getCurrency());
        return this.accountService.createAccount(account);
    }

    @DeleteMapping("/api/account")
    public DataResult<Object> deleteAccount(int accountId, Principal principal) {
        return this.accountService.deleteAccount(accountId, principal.getName());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleNotValidExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorDataResult<Object>(validationErrors, "Validation Errors"), HttpStatus.BAD_REQUEST);
    }


}
