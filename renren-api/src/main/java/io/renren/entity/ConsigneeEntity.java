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
@TableName("tb_consignee")
public class ConsigneeEntity implements Serializable {
    private static final long serialVersionUID = -8782644277319083577L;

    @TableId
    private Long consigneeId;

    private Long userId;

    private String address;

    private String houseNumber;

    private String consigneeName;

    private Integer consigneeSex;

    private Integer count;

    private String consigneePhone;

    private Date createTime;

    private Date updateTime;
}
