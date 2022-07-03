package io.renren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.sm.service.SmGoodsService;
import io.renren.dao.OrderItemDao;
import io.renren.entity.OrderItemEntity;
import io.renren.entity.OrderItemResultEntity;
import io.renren.service.OrderItemService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/7/2 <br>
 * @see io.renren.service.impl <br>
 * @since R9.0<br>
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    @Autowired
    SmGoodsService smGoodsService;

    @Override
    public List<OrderItemResultEntity> queryOrderItemsByOrderId(Long orderId) {
        logger.debug("queryOrderItemsByOrderId start... orderId:[{}]", orderId);
        List<OrderItemResultEntity> orderItemResultEntities = baseMapper.queryOrderItemResultByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderItemResultEntities)){
            logger.debug("queryOrderItemsByOrderId end... result is empty");
            return new ArrayList<>();
        }
        for (OrderItemResultEntity orderItemResultEntity : orderItemResultEntities) {
            orderItemResultEntity.setSmGoodsEntity(smGoodsService.getById(orderItemResultEntity.getGoodsId()));
        }
        return orderItemResultEntities;
    }
}
