package com.star.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.star.sys.pojo.Permission;
import com.star.sys.pojo.User;
import com.star.sys.service.PermissionService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.utils.SystemConstant;
import com.star.sys.utils.TreeNode;
import com.star.sys.utils.TreeNodeBuilder;
import com.star.sys.vo.PermissionVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页树形菜单控制器
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Resource
    private PermissionService permissionService;

    /**
     * 加载首页左侧菜单树
     * @param permissionVo
     * @param session
     * @return
     */
    @RequestMapping("/loadIndexLeftMenu")
    public DataGridViewResult loadIndexLeftMenu(PermissionVo permissionVo, HttpSession session){

        List<Permission> permissions=new ArrayList<>();

        try {
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", SystemConstant.TYPE_MENU);//只查询菜单
            //获取当前登录用户
            User user = (User) session.getAttribute(SystemConstant.LOGINUSER);
            //如果当前登录用户为超级管理员，则能查看所有菜单
            if(user.getType()==SystemConstant.SUPERUSER){
                //查询菜单列表
                permissions = permissionService.list(queryWrapper);
            }else{//普通用户：需要根据当前用户的角色及权限加载菜单列表
                //查询菜单列表
                permissions = permissionService.list(queryWrapper);
            }
            //构建菜单节点集合
            List<TreeNode> treeNodes = new ArrayList<>();
            for (Permission permission : permissions) {
                //判断当前节点是否展开，是则为true，否则为false
                Boolean spread = SystemConstant.OPEN_TRUE == permission.getOpen() ? true : false;

                TreeNode treeNode=new TreeNode();
                treeNode.setId(permission.getId()); //菜单节点id
                treeNode.setPid(permission.getPid()); //父节点id
                treeNode.setHref(permission.getHref()); //菜单路径
                treeNode.setIcon(permission.getIcon()); //菜单图标
                treeNode.setTitle(permission.getTitle()); //菜单名称
                treeNode.setSpread(spread);  //菜单是否展开
                // 将树节点对象添加到树节点集合
                treeNodes.add(treeNode);
            }
            //构建节点菜单层级关系(参数1：节点集合数据源，参数2：根节点编号)
            List<TreeNode> treeNodeList = TreeNodeBuilder.build(treeNodes,1);
            //将节点返回出去
            return new DataGridViewResult(treeNodeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
