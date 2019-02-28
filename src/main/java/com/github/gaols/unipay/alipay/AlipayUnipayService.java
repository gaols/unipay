package com.github.gaols.unipay.alipay;

import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.impl.liuyangkly.model.builder.AlipayTradeCancelRequestBuilder;
import com.alipay.impl.liuyangkly.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.impl.liuyangkly.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.impl.liuyangkly.model.result.AlipayF2FPrecreateResult;
import com.alipay.impl.liuyangkly.model.result.AlipayF2FQueryResult;
import com.alipay.impl.liuyangkly.service.impl.AlipayTradeServiceImpl;
import com.github.gaols.unipay.api.*;
import com.github.gaols.unipay.core.TradeStatusTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://docs.open.alipay.com/api_1/alipay.trade.precreate/
 * https://docs.open.alipay.com/194/105322/ -- a must read
 *
 * @author gaols
 */
public class AlipayUnipayService implements UnipayService {

    private static final Logger logger = LoggerFactory.getLogger(AlipayUnipayService.class);
    private static final AlipayUnipayService orderService = new AlipayUnipayService();
    private static final ConcurrentHashMap<String, AlipayTradeServiceImpl> serviceMap = new ConcurrentHashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public PushOrderResult unifyOrder(OrderContext context, Order order, MchInfo mchInfo) {
        logger.error("Unify order START: " + order.toString());
        AlipayUnifyOrderRequestBuilder builder = new AlipayUnifyOrderRequestBuilder(context, order);
        AlipayTradePrecreateRequestBuilder requestBuilder = builder.build();
        AlipayF2FPrecreateResult result = getAlipayTradeServiceImpl(mchInfo).tradePrecreate(requestBuilder);
        logUnifyOrderResult(result, order);

        PushOrderResult ret = new PushOrderResult();
        ret.setPushOrderStatus(TradeStatusTranslator.translateAlipayPushOrderStatus(result.getTradeStatus()));
        AlipayTradePrecreateResponse response = result.getResponse();
        Map<String, Object> resp = new HashMap<>();
        resp.put("qr_code_url", response.getQrCode());
        resp.put("out_trade_no", response.getOutTradeNo());
        ret.setResponse(resp);
        return ret;
    }

    private AlipayTradeServiceImpl getAlipayTradeServiceImpl(MchInfo mchInfo) {
        String id = mchInfo.getId();
        AlipayTradeServiceImpl tradeService = serviceMap.get(id);
        if (tradeService != null) {
            return tradeService;
        }

        lock.lock();
        try {
            tradeService = serviceMap.get(id);
            if (tradeService != null) {
                return tradeService;
            }
            AlipayTradeServiceImpl impl = new AlipayTradeServiceImpl.ClientBuilder((AlipayMchInfo) mchInfo).build();
            serviceMap.put(id, impl);
            return impl;
        } finally {
            lock.unlock();
        }
    }

    private void logUnifyOrderResult(AlipayF2FPrecreateResult result, Order order) {
        com.alipay.impl.liuyangkly.model.TradeStatus tradeStatus = result.getTradeStatus();
        logger.error(String.format("Unify order[%s] DONE:status=%s", order.getOutTradeNo(), tradeStatus));
    }

    @Override
    public TradeStatus queryOrderStatus(String outTradeNo, MchInfo mchInfo) {
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder().setOutTradeNo(outTradeNo);
        AlipayF2FQueryResult result = getAlipayTradeServiceImpl(mchInfo).queryTradeResult(builder);
        return TradeStatusTranslator.translateAlipayTradeStatus(result.getTradeStatus());
    }

    /**
     * Before cancel this order, we must ensure the order is not paid, if payed success, block it.
     * 每一笔交易一定要闭环，即要么支付成功，要么撤销交易，一定不能有交易一直停留在等待用户付款的状态。
     * 轮询+撤销的流程中，如轮询的结果一直为未付款，撤销一定要紧接着最后一次查询，当中不能有时间间隔。
     *
     * @param outTradeNo 订单编号
     */
    @Override
    public void cancelOrder(String outTradeNo, MchInfo mchInfo) {
        AlipayTradeCancelRequestBuilder builder = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
        AlipayTradeCancelResponse resp = getAlipayTradeServiceImpl(mchInfo).tradeCancel(builder);
        logger.error(String.format("cancel order[%s] with action=%s,retry_flag=%s", outTradeNo, resp.getAction(), resp.getRetryFlag()));
    }

    public static UnipayService create() {
        return orderService;
    }

    private AlipayUnipayService() {
    }
}
