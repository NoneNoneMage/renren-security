package io.renren.common.sm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/2 <br>
 * @see io.renren.common.sm.entity <br>
 * @since R9.0<br>
 */
@Data
@TableName("sm_restaurant")
public class SmRestaurantEntity implements Serializable {
    private static final long serialVersionUID = 3435538813278472609L;

    @TableId
    private Long id;

    @NotBlank(message="门店名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    private String avatarUrl;

    /**
     * 0： 禁用， 1：正常
     */
    private Integer status;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

    private BigDecimal minPriceTip;

    private BigDecimal shippingFeeTip;

    private BigDecimal averagePriceTip;

    private BigDecimal score;

    private String description;

    private String deliveryTime;

    private String bulletin;

    @TableField(exist = false)
    private Long monthSaleNum;

    @TableField(exist = false)
    private String distance;

    @TableField(exist = false)
    private Integer averageDeliveryTime;

    @TableField(exist = false)
    private List<SmGoodsFolderEntity> folders;
}
