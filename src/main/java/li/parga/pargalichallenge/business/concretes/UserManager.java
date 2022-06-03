package li.parga.pargalichallenge.business.concretes;


import li.parga.pargalichallenge.business.abstracts.UserService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataaccess.abstracts.UserDao;

import li.parga.pargalichallenge.dataaccess.abstracts.WalletDao;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
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
public class UserManager implements UserService, UserDetailsService {
    private final UserDao userDao;

    private final WalletDao walletDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserDao userDao, WalletDao walletDao,PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.walletDao = walletDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userDao.findByEmail(username);
        if(user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User Not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
    }

    public DataResult<User> addUser(UserWithoutWalletDto userWithoutWalletDto){


        User user = new User(userWithoutWalletDto.getFirstName(), userWithoutWalletDto.getLastName(),
                userWithoutWalletDto.getPassword(), userWithoutWalletDto.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userDao.save(user);
        Wallet wallet = new Wallet(user,0,"CASH","TRY");
        this.walletDao.save(wallet);
        return new SuccessDataResult<>(this.userDao.save(user));
    }

    @Override
    public DataResult<User> findByUserId(int userId){return new SuccessDataResult<>( this.userDao.findByUserId(userId));
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<>(this.userDao.findByEmail(email));
    }

    public  DataResult<User> deleteUserByEmail(String email){
        this.walletDao.delete(this.walletDao.findByUser_Email(email));
        this.userDao.delete(findByEmail(email).getData());
        return new SuccessDataResult<>(findByEmail(email).getData());
    }

    public DataResult<WalletWithUserNameDto> findBalance(int userId){
        return new SuccessDataResult<>(this.userDao.findBalance(userId));
    }

    @Override
    public void save(User user) {
        this.userDao.save(user);
    }



}
