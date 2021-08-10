package com.star.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.sys.pojo.Notice;
import com.star.sys.pojo.User;
import com.star.sys.service.NoticeService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.utils.JSONResult;
import com.star.sys.utils.SystemConstant;
import com.star.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-08-10
 */
@RestController
@RequestMapping("/sys/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    /***
     * 查询公告列表
     * @param noticeVo
     * @return
     */
    @RequestMapping("/noticeList")
    public DataGridViewResult findNoticeList(NoticeVo noticeVo){
        //创建分页对象
        IPage<Notice> page = new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        //创建条件构造器
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        //标题查询
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        //发布人
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        //开始时间
        queryWrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        //结束时间
        queryWrapper.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        //排序
        queryWrapper.orderByDesc("createtime");
        //分页查询
        noticeService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }


    /***
     * 发布公告
     * @param notice
     * @param session
     * @return
     */
    @RequestMapping("/addNotice")
    public JSONResult addNotice(Notice notice, HttpSession session){
        //获取当前登录用户信息
        User user = (User) session.getAttribute(SystemConstant.LOGINUSER);

        notice.setOpername(user.getName());  //发布人为当前用户
        notice.setCreatetime(new Date());    //发布时间为当前时间

        //保存公告
        if(noticeService.save(notice)){  //判断是否成功
            //成功
            return SystemConstant.ADD_SUCCESS;
        }
        //失败
        return SystemConstant.ADD_ERROR;
    }


    /**
     * 批量删除公告
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        //将字符串拆分成数组
        String[] idsStr=ids.split(",");
        //判断是否删除成功
        if (noticeService.removeByIds(Arrays.asList(idsStr))){  //将字符数组转换为List集合作为参数
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }else {
            //删除失败
            return SystemConstant.DELETE_ERROR;
        }
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    @RequestMapping("/updateNotice")
    public JSONResult updateNotice(Notice notice){
        notice.setModifytime(new Date());  //修改时间为当前时间
        //修改公告
        if(noticeService.updateById(notice)){
            //修改成功
            return SystemConstant.UPDATE_SUCCESS;
        }
        //修改失败
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){

        if(noticeService.removeById(id)){
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }


}

