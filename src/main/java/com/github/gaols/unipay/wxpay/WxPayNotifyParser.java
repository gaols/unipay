package com.github.gaols.unipay.wxpay;

import com.github.gaols.unipay.api.MchInfo;
import com.github.gaols.unipay.api.PayNotifyParser;
import com.github.gaols.unipay.core.WxVendor;
import com.github.gaols.unipay.utils.IOUtils;
import com.github.gaols.unipay.utils.ParaUtils;
import com.github.gaols.unipay.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

/**
 * @author gaols
 * <p>
 * <code>
 * <xml><appid><![CDATA[wxbdb4f2a8c94df91a]]></appid>
 * <bank_type><![CDATA[CFT]]></bank_type>
 * <cash_fee><![CDATA[1]]></cash_fee>
 * <fee_type><![CDATA[CNY]]></fee_type>
 * <is_subscribe><![CDATA[Y]]></is_subscribe>
 * <mch_id><![CDATA[1425619202]]></mch_id>
 * <nonce_str><![CDATA[xxxx]]></nonce_str>
 * <openid><![CDATA[xxxx]]></openid>
 * <out_trade_no><![CDATA[xxxx]]></out_trade_no>
 * <result_code><![CDATA[SUCCESS]]></result_code>
 * <return_code><![CDATA[SUCCESS]]></return_code>
 * <sign><![CDATA[xxxxx]]></sign>
 * <time_end><![CDATA[20190227214432]]></time_end>
 * <total_fee>1</total_fee>
 * <trade_type><![CDATA[NATIVE]]></trade_type>
 * <transaction_id><![CDATA[4200000282201902277982404798]]></transaction_id>
 * </xml>
 * <p>
 * </code>
 */
public class WxPayNotifyParser implements PayNotifyParser {

    private final Map<String, String> parasMap;
    private static final Logger logger = LoggerFactory.getLogger(WxPayNotifyParser.class);

    @Override
    public boolean isSuccess() {
        String resultCode = parasMap.get("result_code");
        return "SUCCESS".equals(resultCode);
    }

    @Override
    public boolean isSignValid(MchInfo mchInfo) {
        WxMchInfo info = (WxMchInfo) mchInfo;
        return WxVendor.getProxy().checkSign(this.parasMap, info.getSignType(), info.getMchKey());
    }

    @Override
    public Map<String, String> getNotifyParasMap() {
        return parasMap;
    }

    public WxPayNotifyParser(HttpServletRequest request) {
        try {
            String xmlData = IOUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
            this.parasMap = parseXml(xmlData);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public WxPayNotifyParser(String xmlData) {
        this.parasMap = parseXml(xmlData);
    }

    private Map<String, String> parseXml(String xmlData) {
        logger.info("wx pay notify xml:\n" + xmlData);
        Map<String, String> parasMap = Collections.unmodifiableMap(XmlUtils.parseXml(xmlData));
        logParas(parasMap);
        return parasMap;
    }

    private void logParas(Map<String, String> parasMap) {
        logger.info(ParaUtils.formatParas(parasMap, "\nWeixin Notify:"));
    }
}
