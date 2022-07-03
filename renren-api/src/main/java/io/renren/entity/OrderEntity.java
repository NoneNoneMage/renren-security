package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("tb_order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 8974727186345896278L;

    @TableId
    private Long orderId;

    private Long userId;

    private Long restaurantId;
    /**
     * 實付
     */
    private BigDecimal price;
    /**
     * 原價
     */
    private BigDecimal oldPrice;
    /**
     * 備註
     */
    private String description;
    /**
     * '状态  0：未支付 1：已支付 2：已完成 3：交易取消 4：支付超時'
     */
    private Integer status;
    /**
     * 支付方式 0：線下 1：PayPal 2：AliPay 3：WeChat Pay 4：Card Pay
     */
    private Integer payType;

    private Date createTime;
    /**
     * 支付超時吃緊
     */
    private Date timeoutTime;

    private Date updateTime;
}
