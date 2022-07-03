package io.renren.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.entity.OrderItemEntity;
import io.renren.entity.OrderItemResultEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
    List<OrderItemResultEntity> queryOrderItemResultByOrderId(Long orderId);
}
