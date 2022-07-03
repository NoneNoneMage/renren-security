package io.renren.common.domain;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/7/2 <br>
 * @see io.renren.common.domain <br>
 * @since R9.0<br>
 */
public final class TbOrderStatusDef {
    /**
     * '状态  0：未支付 1：已支付 2：已完成 3：交易取消 4：支付超時'
     */
    public static final Integer UN_PAYED = 0;

    public static final Integer PAYED = 1;

    public static final Integer FINISHED = 2;

    public static final Integer CANCELLED = 3;

    public static final Integer PAY_TIMEOUT = 4;
}
