package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.UserAddressService;
import com.nstepa.wc.beans.UserAddress;
import com.nstepa.wc.mybatis.support.mappers.UserAddressMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户配送地址 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
