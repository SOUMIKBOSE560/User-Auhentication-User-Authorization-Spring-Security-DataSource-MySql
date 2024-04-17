package net.springsecurity.SpringSecurityRoleBasedAuth.controllers;

import net.springsecurity.SpringSecurityRoleBasedAuth.models.UserInfo;
import net.springsecurity.SpringSecurityRoleBasedAuth.objects.APIResponse;
import net.springsecurity.SpringSecurityRoleBasedAuth.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfo userInfo;

    @PostMapping("/public/create")
    public APIResponse<Object> createUserInfo(@RequestBody UserInfo userInfo){
        try {
            userInfoService.create(userInfo);
            return APIResponse.builder().success(true).status(HttpStatus.CREATED)
                    .message(userInfo)
                    .build();
        }
        catch (Exception e){
            return APIResponse.builder().success(false).status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }


    @DeleteMapping("/public/delete/{id}")
    public APIResponse<Object> deleteUser(@PathVariable(name = "id") String id){
        try{
            return APIResponse.builder().success(true).status(HttpStatus.NOT_FOUND).message(userInfoService.delete(id)).build();
        }
        catch(Exception e){
            return APIResponse.builder().success(false).status(HttpStatus.EXPECTATION_FAILED).message(e.getMessage()).build();
        }
    }

}
