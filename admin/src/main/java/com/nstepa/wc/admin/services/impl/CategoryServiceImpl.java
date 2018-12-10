package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.CategoryService;
import com.nstepa.wc.beans.Category;
import com.nstepa.wc.mybatis.support.mappers.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用的分类表 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
