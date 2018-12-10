package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.GoodsService;
import com.nstepa.wc.beans.Goods;
import com.nstepa.wc.mybatis.support.mappers.GoodsMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品信息表 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
