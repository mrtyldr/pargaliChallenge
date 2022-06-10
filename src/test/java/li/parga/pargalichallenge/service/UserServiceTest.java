package li.parga.pargalichallenge.service;


import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.repository.UserRepository;
import li.parga.pargalichallenge.repository.AccountRepository;
import li.parga.pargalichallenge.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        accountRepository = mock(AccountRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);

    }



}