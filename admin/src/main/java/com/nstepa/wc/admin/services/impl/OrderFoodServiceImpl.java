package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.OrderFoodService;
import com.nstepa.wc.beans.OrderFood;
import com.nstepa.wc.mybatis.support.mappers.OrderFoodMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品详情表 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class OrderFoodServiceImpl extends ServiceImpl<OrderFoodMapper, OrderFood> implements OrderFoodService {

}
