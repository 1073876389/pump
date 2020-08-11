package com.xuwx.pump.service.impl;

import com.xuwx.pump.api.CommonResult;
import com.xuwx.pump.component.CancelOrderSender;
import com.xuwx.pump.dto.OrderParam;
import com.xuwx.pump.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@ClassName OmsPortalOrderServiceImpl
 *@Description 前台订单管理service
 *@Author wx.Xu
 *Date 2020/8/5 14:47
 *Version 1.0
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
       //todo 实际的下单 业务操作
        LOGGER.info("process generateOrder");
        //下单完成之后开启延迟消息,用于用户没有付款时取消订单  以及解除所有资源绑定
        sendDelayMessageCancelOrder(110L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        //todo 执行一系类取消订单操作，具体参考mall项目
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间，假设为60分钟 这里30s 做测试

        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
