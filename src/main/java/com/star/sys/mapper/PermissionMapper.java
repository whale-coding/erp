package com.star.sys.mapper;

import com.star.sys.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Njq
 * @since 2021-08-09
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据菜单id或权限id删除sys_role_permission权限菜单关系表数据
     * @param id
     */
    @Delete("delete from sys_role_permission where pid =#{id}")
    void deleteRolePermissionByPid(Serializable id);

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    @Select("select pid from sys_role_permission where rid = #{roleId}")
    List<Integer> findRolePermissionByRoleId(int roleId);
}
