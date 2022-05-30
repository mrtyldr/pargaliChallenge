package li.parga.pargalichallenge.dataAccess.abstracts;

import li.parga.pargalichallenge.entities.concretes.User;
import li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    User findByUserId(int userId);

    @Query("select new li.parga.pargalichallenge.entities.concretes.dto.WalletWithUserNameDto(u.firstName,u.lastName,w.balance)" +
            "from Wallet w inner join w.user u where" +
            " u.email = :email")
    WalletWithUserNameDto findBalance(@Param("email") String email);
}
