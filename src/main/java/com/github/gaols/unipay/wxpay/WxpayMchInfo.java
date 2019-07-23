package com.github.gaols.unipay.wxpay;

import com.github.gaols.unipay.api.MchInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信的公众号的商户信息。
 */
public class WxpayMchInfo extends MchInfo {
    private String mchKey;
    private String appId;
    private String mchId;
    /**
     * 微信退款必须参数，可到商户中心下载，然后放到项目的classpath下。
     */
    private String keyPath;
    private String signType = "MD5";

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSignType() {
        return StringUtils.defaultIfBlank(signType, "MD5");
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    @Override
    public String getId() {
        return mchId + appId;
    }

    @Override
    public String toString() {
        return "WxMchInfo{" +
                ", appId='" + appId + '\'' +
                ", mchId='" + mchId + '\'' +
                ", signType='" + signType + '\'' +
                '}';
    }

    public void validate() {
        if (StringUtils.isBlank(mchKey)) {
            throw new IllegalArgumentException("mchKey required");
        }
        if (StringUtils.isBlank(mchId)) {
            throw new IllegalArgumentException("mchId required");
        }
        if (StringUtils.isBlank(appId)) {
            throw new IllegalArgumentException("appId required");
        }
    }
}
