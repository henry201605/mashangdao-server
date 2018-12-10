package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.AdminGroupService;
import com.nstepa.wc.beans.AdminGroup;
import com.nstepa.wc.mybatis.support.mappers.AdminGroupMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员分组信息表 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class AdminGroupServiceImpl extends ServiceImpl<AdminGroupMapper, AdminGroup> implements AdminGroupService {

}
