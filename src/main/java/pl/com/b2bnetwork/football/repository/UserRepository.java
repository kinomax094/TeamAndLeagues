package pl.com.b2bnetwork.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.com.b2bnetwork.football.domain.Role;
import pl.com.b2bnetwork.football.domain.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsernameAndPassword(String username, String password);

    Optional<User> findOneByUsername(String name);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.username=:username , u.name=:name , u.role=:role WHERE u.id =:userId")
    int updateUser(@Param("userId") Long id,
                   @Param("username") String username,
                   @Param("name") String name,
                   @Param("role") Role role);
}
