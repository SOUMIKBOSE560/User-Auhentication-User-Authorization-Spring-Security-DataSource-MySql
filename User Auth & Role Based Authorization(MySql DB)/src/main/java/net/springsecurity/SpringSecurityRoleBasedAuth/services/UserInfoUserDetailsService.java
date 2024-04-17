package net.springsecurity.SpringSecurityRoleBasedAuth.services;

import net.springsecurity.SpringSecurityRoleBasedAuth.configs.UserInfoUserDetails;
import net.springsecurity.SpringSecurityRoleBasedAuth.models.UserInfo;
import net.springsecurity.SpringSecurityRoleBasedAuth.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        System.out.println(userInfo);

        return userInfo.map(UserInfoUserDetails::new).orElseThrow(
                () -> new UsernameNotFoundException("USER NOT FOUND : " + username)
       );



    }
}
