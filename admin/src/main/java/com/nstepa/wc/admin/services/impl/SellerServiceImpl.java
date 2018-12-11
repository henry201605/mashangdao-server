package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.SellerService;
import com.nstepa.wc.beans.Seller;
import com.nstepa.wc.mybatis.support.mappers.SellerMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家登录 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

}
