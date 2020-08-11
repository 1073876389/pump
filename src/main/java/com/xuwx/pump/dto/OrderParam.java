package com.xuwx.pump.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *@ClassName OrderParam
 *@Description 订单生成时传入的参数
 *@Author wx.Xu
 *Date 2020/8/5 14:42
 *Version 1.0
 */
@Getter
@Setter
public class OrderParam {
    //收货地址id
    private Long memberReceiveAddressId;
    //优惠券id
    private Long couponId;
    //使用的积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;

}
