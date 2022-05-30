package li.parga.pargalichallenge.Business.abstracts;

import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;

public interface UserService {
    DataResult<User> addUser(UserWithoutWalletDto userWithoutWalletDto);
    DataResult<User> findByUserId(int userId);

    DataResult<User> findByEmail(String email);

    public  DataResult<User> deleteUserByEmail(String email);
    public DataResult<WalletWithUserNameDto> findBalance(String email);

}
