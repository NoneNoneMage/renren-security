package io.renren.common.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.sm.entity.SmRestaurantEntity;

import java.util.Map;

public interface SmRestaurantService extends IService<SmRestaurantEntity> {
    public PageUtils queryPage(Map<String, Object> params);
}
