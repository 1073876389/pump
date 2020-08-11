package com.xuwx.pump.component;


import com.xuwx.pump.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@ClassName CancelOrderReceiver
 *@Description 取消订单消息 处理者   用于从取消订单的消息队列（mall.order.cancel）里接收消息。
 *@Author wx.Xu
 *Date 2020/8/5 10:31
 *Version 1.0
 */
@Component
@RabbitListener(queues = "pump.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("接收延迟消息 orderId:{}",orderId);
        //做实际的业务逻辑操作
        portalOrderService.cancelOrder(orderId);
    }


}
