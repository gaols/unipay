package com.github.gaols.unipay.api;

import com.github.gaols.unipay.alipay.AlipayMchInfo;
import com.github.gaols.unipay.utils.Prop;
import com.github.gaols.unipay.wxpay.WxMchInfo;

public abstract class MchInfo {

    /**
     * 账号标识。
     */
    public abstract String getId();

    /**
     * @param payType  wx or alipay
     * @param filename the properties file name of your pay config which should reside in your classpath
     * @return mch info
     */
    public static MchInfo create(PayType payType, String filename) {
        Prop prop = new Prop(filename);
        switch (payType) {
            case wx:
                WxMchInfo wxMchInfo = new WxMchInfo();
                wxMchInfo.setAppId(prop.get("app_id"));
                wxMchInfo.setMchKey(prop.get("mch_key")); // api key
                wxMchInfo.setMchId(prop.get("mch_id"));
                wxMchInfo.validate();
                return wxMchInfo;
            case alipay:
                AlipayMchInfo alipayMchInfo = new AlipayMchInfo();
                alipayMchInfo.setOpenApiDomain(prop.get("open_api_domain"));
                alipayMchInfo.setMcloudApiDomain(prop.get("mcloud_api_domain"));
                alipayMchInfo.setPid(prop.get("pid"));
                alipayMchInfo.setAppid(prop.get("appid"));
                alipayMchInfo.setPrivateKey(prop.get("private_key"));
                alipayMchInfo.setPublicKey(prop.get("public_key"));
                alipayMchInfo.setAlipayPublicKey(prop.get("alipay_public_key"));
                alipayMchInfo.setSignType(prop.get("sign_type", "RAS2"));
                alipayMchInfo.setMaxQueryRetry(prop.getInt("max_query_retry", 3));
                alipayMchInfo.setQueryDuration(prop.getLong("query_duration", 5000L));
                alipayMchInfo.setMaxCancelRetry(prop.getInt("max_cancel_retry", 3));
                alipayMchInfo.setCancelDuration(prop.getLong("cancel_duration", 2000L));
                alipayMchInfo.validate();
                return alipayMchInfo;
            default:
                throw new IllegalArgumentException("invalid payType");
        }
    }

    /**
     * @param payType  wx or alipay
     * @param filename the properties file name of your pay config which should reside in your classpath
     * @return mch info
     */
    public static MchInfo create(String payType, String filename) {
        return create(PayType.valueOf(payType), filename);
    }
}
