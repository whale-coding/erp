package com.star.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.sys.pojo.Log;
import com.star.sys.pojo.User;
import com.star.sys.service.LogService;
import com.star.sys.service.UserService;
import com.star.sys.utils.*;
import com.star.sys.vo.LoginUserVo;
import com.star.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-08-09
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Resource
    private LogService logService;

    @Resource
    private UserService userService;

    /**
     * 登录
     * @param loginname     用户名
     * @param pwd           密码
     * @param session
     * @return
     */
    @PostMapping("/login")
    public JSONResult login(String loginname, String loginpwd, HttpSession session, HttpServletRequest request){

            //获取当前登录主体对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(loginname,loginpwd);
            try {
            //登录
            subject.login(token);
            //获取当前登录对象
            LoginUserVo userVo =(LoginUserVo) subject.getPrincipal();
            //保存session
            session.setAttribute(SystemConstant.LOGINUSER,userVo.getUser());

            //记录日志
             //内容、操作类型,登录人，登录人id，登录人ip，操作时间
             Log log=new Log("用户登录",SystemConstant.LOGIN_ACTION
                     ,loginname+"-"+userVo.getUser().getName()
                     ,userVo.getUser().getId()
                     ,request.getRemoteAddr(),new Date());
             logService.save(log);

            //登录成功
            return SystemConstant.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //登录失败，用户名或密码错误
            return SystemConstant.LOGIN_ERROR_PASS;
        }

    }


    /**
     * 查询用户列表
     * @param userVo
     * @return
     */
    @RequestMapping("/userList")
    public DataGridViewResult userlist(UserVo userVo){
        try {
            //创建分页对象
            IPage<User> page = new Page<User>(userVo.getPage(),userVo.getLimit());
            //调用分页查询的方法
            IPage<User> userIPage = userService.findUserListByPage(page,userVo);
            return new DataGridViewResult(userIPage.getTotal(),userIPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据部门编号查询该部门下的员工信息
     * @param deptId
     * @return
     */
    @RequestMapping("/loadUserByDeptId")
    public DataGridViewResult loadUserByDeptId(Integer deptId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //只查询普通用户
        queryWrapper.eq("type",SystemConstant.NORMAL_USER);
        //根据部门编号查询
        queryWrapper.eq(deptId!=null,"deptid",deptId);
        //调用查询的方法
        List<User> users = userService.list(queryWrapper);
        //将数据返回
        return new DataGridViewResult(users);
    }


    /**
     * 验证用户名是否存在
     * @param loginName
     * @return
     */
    @RequestMapping("/checkLoginName")
    public String checkLoginName(String loginName){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname",loginName);

        //获取验证的数量
        if(userService.count(queryWrapper)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"登录名称已存在,请重新输入！");

        }else{
            map.put(SystemConstant.EXIST,false);
            map.put(SystemConstant.MESSAGE,"登录名称可以使用！");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public JSONResult addUser(User user){
        //入职日期
        user.setHiredate(new Date());
        //使用随机数作为密码盐值
        String salt = UUIDUtil.randomUUID();
        //默认密码
        user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));
        //盐值
        user.setSalt(salt);
        //用户类型
        user.setType(SystemConstant.NORMAL_USER);//普通用户
        //默认头像
        user.setImgpath("defaultImage.jpg");

        //调用新增用户的方法
        if(userService.save(user)){
            return SystemConstant.ADD_SUCCESS;
        }

        return SystemConstant.ADD_ERROR;
    }


    /**
     * 根据用户ID查询用户详细信息
     * @param id
     * @return
     */
    @RequestMapping("/loadUserById")
    public DataGridViewResult loadUserById(Integer id){
        return new DataGridViewResult(userService.getById(id));
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    public JSONResult updateUser(User user){
        //调用修改用户的方法
        if(userService.updateById(user)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /***
     * 重置密码
     * @param id
     * @return
     */
    @RequestMapping("/resetPwd")
    public JSONResult resetPwd(int id){
        //重新生成UUID
        String salt = UUIDUtil.randomUUID();
        //创建用户对象
        User user = new User();
        user.setId(id);//主键
        user.setSalt(salt);//新的salt盐值
        user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));
        //调用修改用户的方法
        if(userService.updateById(user)){
            return SystemConstant.RESET_SUCCESS;
        }
        return SystemConstant.RESET_ERROR;
    }


}

