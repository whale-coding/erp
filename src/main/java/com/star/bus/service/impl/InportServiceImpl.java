package com.star.bus.service.impl;


import com.star.bus.mapper.GoodsMapper;
import com.star.bus.pojo.Goods;
import com.star.bus.pojo.Inport;
import com.star.bus.mapper.InportMapper;
import com.star.bus.service.InportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Njq
 * @since 2021-09-17
 */
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Inport entity) {
        //根据商品编号查询商品
        Goods goods=goodsMapper.selectById(entity.getGoodsid());
        //增加商品数量
        goods.setNumber(goods.getNumber()+entity.getNumber());
        //更新
        goodsMapper.updateById(goods);
        //保存进货信息
        return super.save(entity);
    }


    @Override
    public boolean updateById(Inport entity) {
        //根据进货ID查询进货
        Inport inport = baseMapper.selectById(entity.getId());
        //根据商品ID查询商品信息
        Goods goods = goodsMapper.selectById(entity.getGoodsid());
        //库存的算法  当前库存-进货单修改之前的数量+修改之后的数量
        goods.setNumber(goods.getNumber()-inport.getNumber()+entity.getNumber());
        goodsMapper.updateById(goods);
        //更新进货单
        return super.updateById(entity);
    }





}
