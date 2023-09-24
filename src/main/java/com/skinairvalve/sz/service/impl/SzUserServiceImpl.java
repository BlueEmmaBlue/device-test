package com.skinairvalve.sz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.skinairvalve.sz.config.Constants;
import com.skinairvalve.sz.dto.user.SzUserInfoDto;
import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.enums.UserRoleEnum;
import com.skinairvalve.sz.mapper.SzUserMapper;
import com.skinairvalve.sz.service.ISzUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@Slf4j
@Service
public class SzUserServiceImpl extends ServiceImpl<SzUserMapper, SzUser> implements ISzUserService {

    @Override
    public SzUser selectEnableUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        LambdaQueryWrapper<SzUser> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(SzUser::getUsername, username)
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
        queryWrapper.eq(SzUser::getUsername, username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addUser(SzUser currentUser, SzUserInfoDto signUpDto) {
        checkAdmin(currentUser);

        SzUser existUserInDb = selectEnableUserByUsername(signUpDto.getUsername());
        Preconditions.checkState(existUserInDb == null, "user already exist");

        SzUser userForAdd = new SzUser();
        userForAdd.setEnabled(Constants.ENABLE);
        userForAdd.setUsername(signUpDto.getUsername());
        userForAdd.setName(signUpDto.getName());
        userForAdd.setPassword(signUpDto.getPassword());
        userForAdd.setAccountRole(signUpDto.getAccountRole());
        userForAdd.setCreateId(currentUser.getId());
        userForAdd.setCreateTime(LocalDateTime.now());
        userForAdd.setUpdateId(currentUser.getId());
        userForAdd.setUpdateTime(userForAdd.getCreateTime());
        return baseMapper.insert(userForAdd) == 1;
    }

    @Override
    public boolean updateUser(SzUser currentUser, SzUserInfoDto userInfo) {
        checkAdmin(currentUser);

        SzUser existUserInDb = selectEnableUserByUsername(userInfo.getUsername());
        Preconditions.checkState(existUserInDb != null, "user should exist");
        BeanUtils.copyProperties(userInfo, existUserInDb);

        return baseMapper.updateById(existUserInDb) == 1;
    }

    @Override
    public boolean deleteUser(SzUser currentUser, String username) {
        checkAdmin(currentUser);

        SzUser existUserInDb = selectEnableUserByUsername(username);
        Preconditions.checkState(existUserInDb != null, "user should exist");

        existUserInDb.setEnabled(Constants.DISABLE);
        return baseMapper.updateById(existUserInDb) == 1;
    }

    @Override
    public List<SzUser> allUser(SzUser currentUser) {
        checkAdmin(currentUser);
        LambdaQueryWrapper<SzUser> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper
                .eq(SzUser::getEnabled, Constants.ENABLE);
        return list(queryWrapper);
    }

    private void checkAdmin(SzUser currentUser) {
        Preconditions.checkNotNull(currentUser);
        Preconditions.checkArgument(StringUtils.equals(currentUser.getAccountRole(),
                UserRoleEnum.ADMIN.name()), "only admin can add user");
    }
}
