package com.xuwx.pump.service;

import com.xuwx.pump.api.CommonResult;
import com.xuwx.pump.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 *  订单管理
 *@Author wx.Xu
 *Date 2020/8/5 11:02
 *Version 1.0
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     * @param orderParam
     * @return
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     * @param orderId
     */
    @Transactional
    void cancelOrder(Long orderId);

}
