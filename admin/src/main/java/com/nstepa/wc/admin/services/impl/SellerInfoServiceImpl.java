package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.SellerInfoService;
import com.nstepa.wc.beans.SellerInfo;
import com.nstepa.wc.mybatis.support.mappers.SellerInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商铺信息表 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class SellerInfoServiceImpl extends ServiceImpl<SellerInfoMapper, SellerInfo> implements SellerInfoService {

}
