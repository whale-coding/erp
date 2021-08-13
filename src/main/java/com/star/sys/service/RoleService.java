package com.star.sys.service;

import com.star.sys.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Njq
 * @since 2021-08-12
 */
public interface RoleService extends IService<Role> {
    /**
     * 保存分配权限关系
     * @param rid
     * @param ids
     * @return
     * @throws Exception
     */
    boolean saveRolePermission(int rid, String ids) throws Exception;
}
