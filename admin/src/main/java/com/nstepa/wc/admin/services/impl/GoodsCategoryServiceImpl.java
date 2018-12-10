package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.GoodsCategoryService;
import com.nstepa.wc.beans.GoodsCategory;
import com.nstepa.wc.mybatis.support.mappers.GoodsCategoryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家的食物分类 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

}
