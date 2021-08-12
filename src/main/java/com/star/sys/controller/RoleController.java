package com.star.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.sys.pojo.Role;
import com.star.sys.service.RoleService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.utils.JSONResult;
import com.star.sys.utils.SystemConstant;
import com.star.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 查询角色列表
     * @param roleVo
     * @return
     */
    @RequestMapping("/rolelist")
    public DataGridViewResult roleList(RoleVo roleVo){
        //创建分页对象
        IPage<Role> page = new Page<Role>(roleVo.getPage(),roleVo.getLimit());
        //创建条件构造器对象
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        //角色编码
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRolecode()),"rolecode",roleVo.getRolecode());
        //角色名称
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRolename()),"rolename",roleVo.getRolename());
        //排序
        queryWrapper.orderByAsc("id");
        //调用分页查询的方法
        roleService.page(page,queryWrapper);
        //将数据返回
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/addRole")
    public JSONResult addRole(Role role){
        role.setCreatetime(new Date());//添加时间
        if(roleService.save(role)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @RequestMapping("/updateRole")
    public JSONResult updateRole(Role role){
        if(roleService.updateById(role)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /***
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        if(roleService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }


}

