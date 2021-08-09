package com.star.sys.controller;


import com.star.sys.pojo.Log;
import com.star.sys.service.LogService;
import com.star.sys.utils.JSONResult;
import com.star.sys.utils.SystemConstant;
import com.star.sys.vo.LoginUserVo;
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

    /**
     * 登录
     * @param loginname     用户名
     * @param pwd           密码
     * @param session
     * @return
     */
    @PostMapping("/login")
    public JSONResult login(String loginname, String pwd, HttpSession session, HttpServletRequest request){

            //获取当前登录主体对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(loginname,pwd);
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

}

