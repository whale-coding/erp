package com.star.stat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.star.bus.pojo.Customer;
import com.star.bus.service.CustomerService;
import com.star.bus.vo.CustomerVo;
import com.star.stat.utils.ExportCustomerUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Resource
    private CustomerService customerService;

    /**
     * 导出客户数据
     */
    @RequestMapping("/exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response) {

        //创建条件构造器
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        //客户姓名(模糊查询)
        wrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        //客户电话(模糊查询)
        wrapper.like(StringUtils.isNotBlank(customerVo.getTelephone()),"telephone",customerVo.getTelephone());
        //联系人(模糊查询)
        wrapper.like(StringUtils.isNotBlank(customerVo.getLinkman()),"linkman",customerVo.getLinkman());
        //根据条件查询客户列表
        List<Customer> customers=customerService.list(wrapper);

        String fileName="客户数据.xls";
        String sheetName="客户数据";
        ByteArrayOutputStream bos= ExportCustomerUtils.exportCustomer(customers,sheetName);

        try {
            fileName= URLEncoder.encode(fileName,"UTF-8");//处理文件名乱码
            //创建封装响应头信息的对象
            HttpHeaders header=new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            header.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
