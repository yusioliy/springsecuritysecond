package com.example.springsecuritysecond.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecuritysecond.mapper.SysMenuMapper;
import com.example.springsecuritysecond.mapper.SysRoleMapper;
import com.example.springsecuritysecond.mapper.SysUserMapper;
import com.example.springsecuritysecond.pojo.SysMenu;
import com.example.springsecuritysecond.pojo.SysRole;
import com.example.springsecuritysecond.pojo.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser currentUser = sysUserMapper.selectOne(wrapper);
        if (currentUser == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        System.out.println(currentUser);
        //获取用户角色和菜单权限
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<SysRole> roleList = sysRoleMapper.selectRoleCodesByUserId(currentUser.getId());
        for (SysRole role : roleList) {
            //客户端使用的角色名称
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleCode());
            authorityList.add(authority);
        }
        List<SysMenu> permsList = sysMenuMapper.selectMenuPermsByUserId(currentUser.getId());
        for (SysMenu perm : permsList) {
            //客户端使用的角色id
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perm.getPerms());
            authorityList.add(authority);
        }
        return new User(currentUser.getUserName(), currentUser.getPassword(), authorityList);
    }
}

