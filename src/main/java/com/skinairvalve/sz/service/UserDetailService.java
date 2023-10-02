package com.skinairvalve.sz.service;

import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.enums.ResultEnum;
import com.skinairvalve.sz.enums.UserRoleEnum;
import com.skinairvalve.sz.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @create on 2023/9/6-7:16 PM
 */
@Slf4j
@Service
public class UserDetailService implements UserDetailsService {

    @Resource
    private ISzUserService szUserService;

    @Override
    public UserDetails loadUserByUsername(String username){
        SzUser szUser = szUserService.selectEnableUserByUsername(username);
        if(szUser == null){
            return null;
        }else{
            return new User(szUser.getUsername(),szUser.getPassword(), Collections.emptyList());
        }
    }


    public SzUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if(authentication.isAuthenticated()) {
            String username = authentication
                    .getName();
            log.info("current username is => {}", username);
            return szUserService.selectByUsername(username);
        }else{
            return null;
        }
    }

    public void checkAdmin(){
        SzUser user = getCurrentUser();
        if(user != null && StringUtils.equals(user.getAccountRole(), UserRoleEnum.ADMIN.name())){
            return;
        }else{
            throw new SystemException(ResultEnum.FAIL.getCode(), "no auth");
        }
    }
}
