package io.renren.entity;

import io.renren.common.sm.entity.SmGoodsEntity;
import lombok.Data;

import java.io.Serializable;

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
public class OrderItemResultEntity extends OrderItemEntity implements Serializable {
    private static final long serialVersionUID = -7112854150536676601L;

    private SmGoodsEntity smGoodsEntity;
}
