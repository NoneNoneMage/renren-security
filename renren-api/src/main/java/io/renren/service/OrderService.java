package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.entity.OrderEntity;
import io.renren.entity.OrderResultEntity;

import java.util.List;

public interface OrderService extends IService<OrderEntity> {
    List<OrderResultEntity> qryOrderListByUserId(Long userId);
}
