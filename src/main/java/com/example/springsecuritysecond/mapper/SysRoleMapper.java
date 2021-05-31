package com.example.springsecuritysecond.mapper;

import com.example.springsecuritysecond.pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-27
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectRoleCodesByUserId(Integer userId);
}
