package net.springsecurity.SpringSecurityRoleBasedAuth.repositories;

import net.springsecurity.SpringSecurityRoleBasedAuth.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    @Query(value = "SELECT * FROM springsecurity_db.user_table u WHERE u.user_name=:username",nativeQuery = true)
    Optional<UserInfo> findByName(String username);
}
