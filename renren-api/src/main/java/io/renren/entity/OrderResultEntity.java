package io.renren.entity;

import io.renren.common.sm.entity.SmRestaurantEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/7/2 <br>
 * @see io.renren.entity <br>
 * @since R9.0<br>
 */
@Data
public class OrderResultEntity extends OrderEntity implements Serializable {

    private static final long serialVersionUID = -1706045108924278795L;

    private List<OrderItemResultEntity> orderItemResultEntityList;

    private SmRestaurantEntity smRestaurantEntity;
}
