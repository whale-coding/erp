package com.star.sys.service;

import com.star.sys.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Njq
 * @since 2021-08-09
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据角色id查询该角色拥有的权限id集合
     * @param roleId
     * @return
     */
    List<Integer> findRolePermissionByRoleId(int roleId);

}
