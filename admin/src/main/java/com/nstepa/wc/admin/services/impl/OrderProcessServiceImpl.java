package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.OrderProcessService;
import com.nstepa.wc.beans.OrderProcess;
import com.nstepa.wc.mybatis.support.mappers.OrderProcessMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单--进度详情 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class OrderProcessServiceImpl extends ServiceImpl<OrderProcessMapper, OrderProcess> implements OrderProcessService {

}
