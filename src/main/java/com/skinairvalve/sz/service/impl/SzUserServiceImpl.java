package com.skinairvalve.sz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.base.Preconditions;
import com.skinairvalve.sz.config.Constants;
import com.skinairvalve.sz.dto.user.SignUpDto;
import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.enums.UserRoleEnum;
import com.skinairvalve.sz.mapper.SzUserMapper;
import com.skinairvalve.sz.service.ISzUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@Slf4j
@Service
public class SzUserServiceImpl extends ServiceImpl<SzUserMapper, SzUser> implements ISzUserService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public SzUser selectEnableUserByUsername(String username) {
        if(StringUtils.isBlank(username)){
            return null;
        }
        LambdaQueryWrapper<SzUser> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(SzUser::getUsername,username)
                .eq(SzUser::getEnabled, Constants.ENABLE);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public SzUser selectByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        LambdaQueryWrapper<SzUser> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(SzUser::getUsername,username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addUser(SzUser currentUser, SignUpDto signUpDto) {
        Preconditions.checkNotNull(currentUser);
        Preconditions.checkArgument(StringUtils.equals(currentUser.getAccountRole(),
                UserRoleEnum.ADMIN.name()),"only admin can add user");
        SzUser existUserInDb = selectByUsername(signUpDto.getUsername());
        Preconditions.checkState(existUserInDb == null,"user already exist");

        SzUser userForAdd = new SzUser();
        userForAdd.setEnabled(Constants.ENABLE);
        userForAdd.setUsername(signUpDto.getUsername());
        userForAdd.setName(signUpDto.getName());
        userForAdd.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userForAdd.setAccountRole(signUpDto.getRole());
        userForAdd.setCreateId(currentUser.getId());
        userForAdd.setCreateTime(LocalDateTime.now());
        userForAdd.setUpdateId(currentUser.getId());
        userForAdd.setUpdateTime(userForAdd.getCreateTime());
        return baseMapper.insert(userForAdd) == 1;
    }
}
