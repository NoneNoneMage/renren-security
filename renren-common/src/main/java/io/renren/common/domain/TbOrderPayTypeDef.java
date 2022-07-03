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
public final class TbOrderPayTypeDef {
    /**
     * 支付方式 0：線下 1：PayPal 2：AliPay 3：WeChat Pay 4：Card Pay
     */
    public static final Integer OFFLINE_PAYMENT = 0;

    public static final Integer PAYPAL = 1;

    public static final Integer ALIPAY = 2;

    public static final Integer WECHAT_PAY = 3;

    public static final Integer CARD_PAY = 4;
}
