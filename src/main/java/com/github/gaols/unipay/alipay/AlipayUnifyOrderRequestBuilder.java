package com.github.gaols.unipay.alipay;

import com.alipay.impl.liuyangkly.model.GoodsDetail;
import com.alipay.impl.liuyangkly.model.builder.AlipayTradePrecreateRequestBuilder;
import com.github.gaols.unipay.api.LineItem;
import com.github.gaols.unipay.api.Order;
import com.github.gaols.unipay.api.OrderContext;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaols
 */
public class AlipayUnifyOrderRequestBuilder {
    private final OrderContext context;
    private final Order order;

    public AlipayUnifyOrderRequestBuilder(OrderContext context, Order order) {
        this.context = context;
        this.order = order;
    }

    public AlipayTradePrecreateRequestBuilder build() {
        AlipayTradePrecreateRequestBuilder builder = new MyAlipayTradePrecreateRequestBuilder()
                .setOutTradeNo(order.getOutTradeNo())
                .setSubject(order.getSubject())
                .setBody(order.getBody())
                .setTotalAmount(convertTotalAmount(order.getTotalFee()))
                .setDiscountableAmount(convertTotalAmount(order.getTotalFee()))
                .setNotifyUrl(context.getNotifyUrl())
                .setTimeoutExpress(context.getPayTimeout());

        if (StringUtils.isNotBlank(context.getOperatorId())) {
            builder.setOperatorId(context.getOperatorId());
        }

        List<GoodsDetail> goodDetailsList = createGoodDetailsList(order.getLineItemList());
        if (goodDetailsList != null && !goodDetailsList.isEmpty()) {
            builder.setGoodsDetailList(goodDetailsList);
        }

        return builder;
    }

    /**
     * amount是单位是分，这里需要将其转化元。
     *
     * @param amount the amount of cents.
     * @return Yuan
     */
    private static String convertTotalAmount(long amount) {
        double m = amount * 1.0d / 100.0d;
        BigDecimal value = BigDecimal.valueOf(m);
        return new DecimalFormat("#.##").format(value);
    }

    private GoodsDetail createGoodsDetail(LineItem item) {
        return GoodsDetail.newInstance(item.getGoodsId(), item.getGoodsName(), item.getPrice(), item.getQuantity());
    }

    private List<GoodsDetail> createGoodDetailsList(List<LineItem> lineItemList) {
        List<GoodsDetail> goodDetailsList = null;
        if (lineItemList != null && !lineItemList.isEmpty()) {
            goodDetailsList = new ArrayList<>();
            for (LineItem item : lineItemList) {
                goodDetailsList.add(createGoodsDetail(item));
            }
        }
        return goodDetailsList;
    }
}
