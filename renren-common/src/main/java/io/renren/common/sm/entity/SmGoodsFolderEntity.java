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
import java.util.Date;
import java.util.List;

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
@TableName("sm_goods_folder")
public class SmGoodsFolderEntity implements Serializable {
    private static final long serialVersionUID = 167639663110779573L;

    @TableId
    private Long id;

    @NotNull(message="门店不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long restaurantId;

    @NotBlank(message="目录名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    // 目录类型，暂时没用
    private Integer type;

    /**
     * 0： 禁用， 1：正常
     */
    private Integer status;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

    @TableField(exist = false)
    private List<SmGoodsEntity> foods;
}
