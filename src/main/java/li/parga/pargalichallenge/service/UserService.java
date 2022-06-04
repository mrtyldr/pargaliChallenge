package li.parga.pargalichallenge.service;



import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.entities.dto.AccountWithUserNameDto;
import li.parga.pargalichallenge.repository.UserRepository;

import li.parga.pargalichallenge.repository.AccountRepository;
import li.parga.pargalichallenge.entities.User;
import li.parga.pargalichallenge.entities.Account;
import li.parga.pargalichallenge.entities.dto.UserWithoutAccountDto;

import li.parga.pargalichallenge.exceptions.NotFoundException;
import li.parga.pargalichallenge.exceptions.NotUniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User Not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public DataResult<Object> addUser(UserWithoutAccountDto userWithoutAccountDto) {

        if (userRepository.findByEmail(userWithoutAccountDto.getEmail()) != null) {
            throw new NotUniqueException("email is not unique");
        }
        User user = new User(userWithoutAccountDto.getFirstName(), userWithoutAccountDto.getLastName(),
                userWithoutAccountDto.getPassword(), userWithoutAccountDto.getEmail());


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        Account account = new Account(user, 0, "CASH", "TRY");
        this.accountRepository.save(account);
        return new SuccessDataResult<>(this.userRepository.save(user));
    }


    public DataResult<User> findByUserId(int userId) {
        return new SuccessDataResult<>(this.userRepository.findByUserId(userId));
    }


    public DataResult<User> findByEmail(String email) {
        var user = this.userRepository.findByEmail(email);
        if (user == null)
            throw new NotFoundException("User Not Found");
        return new SuccessDataResult<>(user);
    }

    public DataResult<User> deleteUserByEmail(String email) {
        var wallets = this.accountRepository.findByUser_Email(email);
        this.accountRepository.deleteAll(wallets);
        this.userRepository.delete(findByEmail(email).getData());
        return new SuccessDataResult<>(findByEmail(email).getData());
    }

    public DataResult<AccountWithUserNameDto> findBalance(String email) {
        return new SuccessDataResult<>(this.userRepository.findBalance(email));
    }





}
