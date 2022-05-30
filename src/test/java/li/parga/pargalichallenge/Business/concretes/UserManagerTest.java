package li.parga.pargalichallenge.Business.concretes;

import li.parga.pargalichallenge.Business.abstracts.UserService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataAccess.abstracts.UserDao;
import li.parga.pargalichallenge.dataAccess.abstracts.WalletDao;
import li.parga.pargalichallenge.entities.concretes.User;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;



@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserManagerTest {

    private UserDao userDao;
    private UserService userService;

    private WalletDao walletDao;
    @BeforeAll
   public void setUp(){
        userDao = Mockito.mock(UserDao.class);
        walletDao = Mockito.mock(WalletDao.class);
        userService = new UserManager(userDao,walletDao);
        }




    @Test
   public void findByEmail() {
        User user = new User(1,"Murat","YILDIRIM","123456","mrtyldrm9@gmail.com");
        Mockito.when(userDao.findByUserId(1)).thenReturn(user);
        DataResult<User> result = this.userService.findByUserId(1);
        assertEquals(new SuccessDataResult<>(user),result);
        Mockito.verify(userDao).findByUserId(1);

    }
}