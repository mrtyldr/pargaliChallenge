package li.parga.pargalichallenge.Business.concretes;

import li.parga.pargalichallenge.Business.abstracts.UserService;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataAccess.abstracts.UserDao;
import li.parga.pargalichallenge.dataAccess.abstracts.WalletDao;
import li.parga.pargalichallenge.entities.concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class UserManagerTest {

    private UserDao userDao;
    private WalletDao walletDao;
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userDao = mock(UserDao.class);
        walletDao = mock(WalletDao.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserManager(userDao, walletDao,passwordEncoder);
    }

    @Test
    public void findByEmail() {
        var user = new User(1, "Murat", "YILDIRIM", "123456", "mrtyldrm9@gmail.com");
        when(userDao.findByUserId(1)).thenReturn(user);
        var result = this.userService.findByUserId(1);
        assertEquals(new SuccessDataResult<>(user), result);
        verify(userDao).findByUserId(1);

    }
}