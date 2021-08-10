package com.star.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.sys.pojo.Notice;
import com.star.sys.service.NoticeService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}

