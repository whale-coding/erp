package com.star.sys.mapper;

import com.star.sys.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Njq
 * @since 2021-08-12
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 删除角色用户关系表的数据
     * @param id
     */
    @Delete("delete from sys_role_user where rid = #{id}")
    void deleteRoleUserByRoleId(Serializable id);

    /**
     * 删除角色权限关系表的数据
     * @param id
     */
    @Delete("delete from sys_role_permission where rid = #{id}")
    void deleteRolePermissionByRoleId(Serializable id);
}
