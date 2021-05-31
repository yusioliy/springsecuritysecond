package com.example.springsecuritysecond.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:list')")
    public String userList() {
        return "访问用户查询界面成功!";
    }


    @GetMapping("/add")
    @PreAuthorize("hasAuthority('user:add')")
    public String userAdd() {
        return  "访问用户新增界面成功!";
    }


    /**
     * 测试无权限访问，数据库中权限是user:update
     * @return
     */
    @GetMapping("/update")
    @PreAuthorize("hasAuthority('user:edit')")//判断当前用户有没有user:edit权限，user:edit只是一个字符串
    public String userUpdate() {
        return  "访问用户修改界面成功!";
    }


    @GetMapping("/delete")
    @Secured("ROLE_admin")//判断当前用户是不是admin角色，ROLE_  固定前缀
    public String userDelete() {
        return "访问用户删除界面成功!";
    }
}

