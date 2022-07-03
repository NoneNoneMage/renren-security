package io.renren.common.sm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.common.sm.entity.SmRestaurantEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmRestaurantDao extends BaseMapper<SmRestaurantEntity> {
}
