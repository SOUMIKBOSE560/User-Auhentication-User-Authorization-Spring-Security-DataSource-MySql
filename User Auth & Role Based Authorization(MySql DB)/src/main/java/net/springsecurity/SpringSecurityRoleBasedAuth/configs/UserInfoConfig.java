package net.springsecurity.SpringSecurityRoleBasedAuth.configs;

import net.springsecurity.SpringSecurityRoleBasedAuth.models.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInfoConfig {
    @Bean
    public UserInfo userInfo(){
        return new UserInfo();
    }
}
