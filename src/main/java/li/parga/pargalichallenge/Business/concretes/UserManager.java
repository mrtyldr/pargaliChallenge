package li.parga.pargalichallenge.Business.concretes;


import li.parga.pargalichallenge.Business.abstracts.UserService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataAccess.abstracts.UserDao;

import li.parga.pargalichallenge.dataAccess.abstracts.WalletDao;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.Wallet;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserId;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserManager implements UserService {
    private final UserDao userDao;

    private final WalletDao walletDao;

    @Autowired
    public UserManager(UserDao userDao, WalletDao walletDao) {
        this.userDao = userDao;
        this.walletDao = walletDao;
    }

    public DataResult<User> addUser(UserWithoutWalletDto userWithoutWalletDto){


        User user = new User(userWithoutWalletDto.getUserId(), userWithoutWalletDto.getFirstName(), userWithoutWalletDto.getLastName(),
                userWithoutWalletDto.getPassword(), userWithoutWalletDto.getEmail());
        this.userDao.save(user);
        Wallet wallet = new Wallet(user,0);
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
        this.userDao.delete(findByEmail(email).getData());
        return new SuccessDataResult<>(findByEmail(email).getData());
    }

    public DataResult<WalletWithUserNameDto> findBalance(String email){
        return new SuccessDataResult<>(this.userDao.findBalance(email));
    }


}
