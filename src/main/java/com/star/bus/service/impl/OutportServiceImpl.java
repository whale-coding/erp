package com.star.bus.service.impl;

import com.star.bus.mapper.GoodsMapper;
import com.star.bus.mapper.InportMapper;
import com.star.bus.pojo.Goods;
import com.star.bus.pojo.Inport;
import com.star.bus.pojo.Outport;
import com.star.bus.mapper.OutportMapper;
import com.star.bus.service.OutportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.common.utils.SystemConstant;
import com.star.common.utils.WebUtils;
import com.star.sys.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Njq
 * @since 2021-09-17
 */
@Service
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

    @Resource
    private InportMapper inportMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public void addOutPort(Integer id, Integer number, String remark) {
        //1,根据进货单ID查询进货单信息
        Inport inport = this.inportMapper.selectById(id);
        //2,根据商品ID查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-number);
        this.goodsMapper.updateById(goods);
        //3,添加退货单信息
        Outport entity=new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setNumber(number);
        //获取当前的登录用户
        User user=(User) WebUtils.getSession().getAttribute(SystemConstant.LOGINUSER);
        entity.setOperateperson(user.getName());
        entity.setOutportprice(inport.getInportprice());
        entity.setOutputtime(new Date());
        entity.setPaytype(inport.getPaytype());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);
    }
}
