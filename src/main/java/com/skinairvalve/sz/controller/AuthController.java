package com.skinairvalve.sz.controller;

import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.dto.user.LoginDto;
import com.skinairvalve.sz.dto.user.SignUpDto;
import com.skinairvalve.sz.service.ISzUserService;
import com.skinairvalve.sz.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @create on 2023/9/6-7:53 PM
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api("auth-api")
public class AuthController {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ISzUserService szUserService;

    @Resource
    private UserDetailService  userDetailService;

    private static final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/login")
    public ApiResult<String> authenticateUser(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolderStrategy securityContextHolderStrategy =
                SecurityContextHolder.getContextHolderStrategy();

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return ApiResult.ok("User signed-in successfully!.");
    }

    @PostMapping("/signup")
    public ApiResult<String> registerUser(@Valid  @RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(! szUserService.addUser(userDetailService.getCurrentUser(),signUpDto)){
            return ApiResult.fail("Username is already taken!");
        }
        return ApiResult.ok("User registered successfully");

    }

    @ApiOperation(value = "get current user")
    @GetMapping("/currentUser")
    public ApiResult<String> currentUser(HttpServletRequest httpServletRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResult.ok(authentication.getName());
    }
}
