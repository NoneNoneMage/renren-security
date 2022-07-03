package io.renren.common.sm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/4 <br>
 * @see io.renren.common.sm.entity <br>
 * @since R9.0<br>
 */
@Data
@TableName("sm_goods")
public class SmGoodsEntity implements Serializable {
    private static final long serialVersionUID = -1673174201633088982L;

    @TableId
    private Long id;

    @NotNull(message="目录不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long folderId;

    @NotBlank(message="菜名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    private BigDecimal price;

    private BigDecimal oldPrice;

    private String description;

    private String info;

    private String avatarUrl;

    private String imageUrl;
    /**
     * 0： 禁用， 1：正常
     */
    private Integer status;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

    @TableField(exist = false)
    private Integer sellCount;

    private Integer rating;
}
