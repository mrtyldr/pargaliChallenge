package li.parga.pargalichallenge.repository;

import li.parga.pargalichallenge.entities.User;

import li.parga.pargalichallenge.entities.dto.AccountWithUserNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User findByUserId(int userId);


    @Query("select new li.parga.pargalichallenge.entities.dto.AccountWithUserNameDto(u.firstName,u.lastName,w.balance)" +
            "from Account w inner join w.user u where" +
            " u.email = :email and w.accountType = :accountType and w.currency = :currency ")
    AccountWithUserNameDto findBalance(String email,String accountType,String currency);

}
