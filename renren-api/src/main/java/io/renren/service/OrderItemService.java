package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.entity.OrderItemEntity;
import io.renren.entity.OrderItemResultEntity;

import java.util.List;

public interface OrderItemService extends IService<OrderItemEntity> {
    List<OrderItemResultEntity> queryOrderItemsByOrderId(Long orderId);
}
