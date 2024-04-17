package net.springsecurity.SpringSecurityRoleBasedAuth.services;

import net.springsecurity.SpringSecurityRoleBasedAuth.configs.SpringSecurityConfig;
import net.springsecurity.SpringSecurityRoleBasedAuth.models.UserInfo;
import net.springsecurity.SpringSecurityRoleBasedAuth.repositories.UserInfoRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SpringSecurityConfig config;

    //CREATE
    public String create(UserInfo user){
    try{
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userInfoRepository.save(user);
        return "CREATED";
    }
    catch (Exception e){
        return e.getMessage();
    }
    }

    //UPDATE
    public String update(String id,UserInfo userInfo){
        try{
            UserInfo user = getById(id);
            if(user != null){
                user.setUsername(userInfo.getUsername());
                user.setPassword(userInfo.getPassword());
            }
            assert user != null;
            userInfoRepository.save(user);
            return "UPDATED";

        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    //DELETE
    public String delete(String id){
        try{
            UserInfo userInfo = getById(id);
            userInfoRepository.delete(userInfo);
            return "DELETED";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    //GET BY ID
    public UserInfo getById(String id){
        try{
            Optional<UserInfo> user = userInfoRepository.findById(id);
            return user.orElse(null);
        }
        catch(Exception e){
            return null;
        }
    }

    //ALL DATA
    public List<UserInfo> getAllUsers(){
        try{
            return userInfoRepository.findAll();
        }
        catch (Exception e){
            return null;
        }
    }

}
