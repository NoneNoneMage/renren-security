package io.renren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.domain.TbOrderStatusDef;
import io.renren.common.sm.service.SmGoodsService;
import io.renren.common.sm.service.SmRestaurantService;
import io.renren.dao.OrderDao;
import io.renren.entity.OrderEntity;
import io.renren.entity.OrderResultEntity;
import io.renren.service.OrderItemService;
import io.renren.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    SmGoodsService smGoodsService;

    @Autowired
    SmRestaurantService smRestaurantService;

    private static final Long LIMIT = 30l;

    @Override
    public List<OrderResultEntity> qryOrderListByUserId(Long userId) {
        logger.debug("qryOrderListByUserId start... userId:[{}]", userId);
        Date now = new Date();
        List<OrderResultEntity> orderResultEntities = baseMapper.queryRecentOrderByUSerId(userId, LIMIT);
        if (CollectionUtils.isEmpty(orderResultEntities)) {
            logger.debug("qryOrderListByUserId end... result is empty");
            return new ArrayList<>();
        }
        for (OrderResultEntity orderResultEntity : orderResultEntities) {
            if (TbOrderStatusDef.UN_PAYED.equals(orderResultEntity) && now.before(orderResultEntity.getTimeoutTime())) {
                orderResultEntity.setStatus(TbOrderStatusDef.PAY_TIMEOUT);
                // 補齊OrderItem
                orderResultEntity.setOrderItemResultEntityList(orderItemService.queryOrderItemsByOrderId(orderResultEntity.getOrderId()));
                // 補齊Restaurant
                orderResultEntity.setSmRestaurantEntity(smRestaurantService.getById(orderResultEntity.getRestaurantId()));
            }
        }
        logger.debug("qryOrderListByUserId end... result is [{}]", orderResultEntities);
        return orderResultEntities;
    }
}
