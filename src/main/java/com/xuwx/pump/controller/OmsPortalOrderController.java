package com.xuwx.pump.controller;

import com.xuwx.pump.dto.OrderParam;
import com.xuwx.pump.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@ClassName OmsPortalOrderController
 *@Description 订单管理 controller
 *@Author wx.Xu
 *Date 2020/8/5 15:12
 *Version 1.0
 */
@Controller
@RequestMapping("/order")
@Api(tags = "OmsPortalOrderController",description = "订单管理")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object generateOrder(@RequestBody OrderParam orderParam) {
        return portalOrderService.generateOrder(orderParam);
    }
}
