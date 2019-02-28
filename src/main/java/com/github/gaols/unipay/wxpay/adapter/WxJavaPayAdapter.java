package com.github.gaols.unipay.wxpay.adapter;

import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayOrderCloseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayOrderQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.gaols.unipay.api.*;
import com.github.gaols.unipay.core.PushOrderStatus;
import com.github.gaols.unipay.core.TradeStatusTranslator;
import com.github.gaols.unipay.wxpay.WxMchInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxJavaPayAdapter implements UnipayService {

    private static final Logger logger = LoggerFactory.getLogger(WxJavaPayAdapter.class);

    @Override
    public PushOrderResult unifyOrder(OrderContext context, Order order, MchInfo mchInfo) {
        WxPayService wxPayService = payService(mchInfo);
        wxPayService.getConfig().setNotifyUrl(context.getNotifyUrl());
        PushOrderResult ret = new PushOrderResult();
        try {
            WxPayNativeOrderResult pushResult = wxPayService.createOrder(createWxPayUnifiedOrderRequest(context, order));// 内部会自动签名
            ret.setPushOrderStatus(PushOrderStatus.SUCCESS);
            Map<String, Object> resp = new HashMap<>();
            resp.put("qr_code_url", pushResult.getCodeUrl());
            ret.setResponse(resp);
        } catch (WxPayException e) {
            ret.setPushOrderStatus(PushOrderStatus.FAILED);
            logger.error("create order failed", e);
        }

        return ret;
    }

    @Override
    public TradeStatus queryOrderStatus(String outTradeNo, MchInfo mchInfo) {
        WxPayService wxPayService = payService(mchInfo);
        WxPayOrderQueryRequest queryRequest = new WxPayOrderQueryRequest();
        queryRequest.setOutTradeNo(outTradeNo);
        try {
            WxPayOrderQueryResult result = wxPayService.queryOrder(queryRequest);
            String tradeState = result.getTradeState();
            logger.info(String.format("Wx trade[%s] state is: %s", outTradeNo, tradeState));
            return TradeStatusTranslator.translateWxTradeStatus(tradeState);
        } catch (WxPayException e) {
            logger.error("query order status error,outTradeNo={}", outTradeNo, e);
            return TradeStatus.UNKNOWN;
        }
    }

    @Override
    public void cancelOrder(String outTradeNo, MchInfo mchInfo) {
        WxPayService wxPayService = payService(mchInfo);
        WxPayOrderCloseRequest queryRequest = new WxPayOrderCloseRequest();
        queryRequest.setOutTradeNo(outTradeNo);
        try {
            wxPayService.closeOrder(queryRequest);
        } catch (WxPayException e) {
            logger.error("query order status error,outTradeNo={}", outTradeNo, e);
        }
    }

    private WxPayUnifiedOrderRequest createWxPayUnifiedOrderRequest(OrderContext context, Order order) {
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody(order.getSubject()); // 商品描述，例如：腾讯充值中心-QQ会员充值，即最好是应用名+商品名
        orderRequest.setOutTradeNo(order.getOutTradeNo());// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
        orderRequest.setTotalFee((int) order.getTotalFee());//分
        orderRequest.setSpbillCreateIp(context.getPayerIp());

        String orderDetail = buildOrderDetail(order);
        if (StringUtils.isNotBlank(orderDetail)) {
            orderRequest.setDetail(orderDetail);
        }
        return orderRequest;
    }

    private WxPayService payService(MchInfo mchInfo) {
        WxMchInfo info = (WxMchInfo) mchInfo;
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(info.getAppId());
        payConfig.setMchId(info.getMchId());
        payConfig.setMchKey(info.getMchKey());
        payConfig.setSignType(info.getSignType());
        payConfig.setTradeType("NATIVE");
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    private String buildOrderDetail(Order order) {
        List<LineItem> items = order.getLineItemList();
        if (items == null || items.isEmpty()) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCostPrice((int) order.getTotalFee());
        orderDetail.setGoodDetails(getGoodDetails(items));

        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
                .toJson(orderDetail);
    }

    private List<GoodDetail> getGoodDetails(List<LineItem> items) {
        List<GoodDetail> goodDetailList = new ArrayList<>();
        for (LineItem item : items) {
            GoodDetail goodDetail = new GoodDetail();
            goodDetail.setGoodsId(item.getGoodsId());
            goodDetail.setGoodsName(item.getGoodsName());
            goodDetail.setPrice(item.getPrice());
            goodDetail.setQuantity(item.getQuantity());
            goodDetail.setWxpayGoodsId(item.getVendorGoodsId());
            goodDetailList.add(goodDetail);
        }

        return goodDetailList;
    }

}
