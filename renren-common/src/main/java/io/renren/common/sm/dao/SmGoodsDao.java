package io.renren.common.sm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.common.sm.entity.SmGoodsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmGoodsDao extends BaseMapper<SmGoodsEntity> {
}
