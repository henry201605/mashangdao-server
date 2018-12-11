package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.OrderCommentService;
import com.nstepa.wc.beans.OrderComment;
import com.nstepa.wc.mybatis.support.mappers.OrderCommentMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单--评论 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentMapper, OrderComment> implements OrderCommentService {

}
