package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
@TableName("tb_order_item")
public class OrderItemEntity implements Serializable {
    private static final long serialVersionUID = 2984433496183826768L;

    @TableId
    private Long orderItemId;

    private Long orderId;

    private Long goodsId;

    private Integer status;

    private Integer amount;

    private Date createTime;
}
