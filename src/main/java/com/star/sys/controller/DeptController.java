package com.star.sys.controller;


import com.star.sys.pojo.Dept;
import com.star.sys.service.DeptService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.utils.TreeNode;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Resource
    private DeptService deptService;


    /***
     * 加载left.html左侧菜单树
     * @return
     */
    @RequestMapping("/loadDeptTreeLeft")
    public DataGridViewResult loadDeptTreeLeft(){
        //查询所有部门
        List<Dept> deptList = deptService.list();
        //创建节点集合
        List<TreeNode> treeNodes = new ArrayList<>();
        //循环遍历部门集合
        for (Dept dept : deptList) {
            //是否展开 1为展开状态
            Boolean spread = dept.getOpen()==1?true:false;

            //封装树节点
            TreeNode treeNode=new TreeNode();
            treeNode.setId(dept.getId());  //部门编号
            treeNode.setPid(dept.getPid()); //父级部门编号
            treeNode.setTitle(dept.getTitle()); //部门名称
            treeNode.setSpread(spread); //展开状态
            //将树节点对象添加到节点集合
            treeNodes.add(treeNode);

        }
        //将树节点返回
        return new DataGridViewResult(treeNodes);
    }

}

