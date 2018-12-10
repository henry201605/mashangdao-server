package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.AdminLogService;
import com.nstepa.wc.beans.AdminLog;
import com.nstepa.wc.mybatis.support.mappers.AdminLogMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员操作日志 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

}
