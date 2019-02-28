package com.github.gaols.unipay.alipay;

import com.github.gaols.unipay.api.MchInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * 支付宝账号商户信息。
 */
public class AlipayMchInfo extends MchInfo {

    private String openApiDomain;   // 支付宝openapi域名
    private String mcloudApiDomain;  // 支付宝mcloudmonitor域名
    private String pid;             // 商户partner id
    private String appid;           // 商户应用id

    private String privateKey;      // RSA私钥，用于对商户请求报文加签
    private String publicKey;       // RSA公钥，仅用于验证开发者网关
    private String alipayPublicKey; // 支付宝RSA公钥，用于验签支付宝应答
    private String signType;     // 签名类型

    private int maxQueryRetry;   // 最大查询次数
    private long queryDuration;  // 查询间隔（毫秒）

    private int maxCancelRetry;  // 最大撤销次数
    private long cancelDuration; // 撤销间隔（毫秒）

    public String getOpenApiDomain() {
        return openApiDomain;
    }

    public void setOpenApiDomain(String openApiDomain) {
        this.openApiDomain = openApiDomain;
    }

    public String getMcloudApiDomain() {
        return mcloudApiDomain;
    }

    public void setMcloudApiDomain(String mcloudApiDomain) {
        this.mcloudApiDomain = mcloudApiDomain;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public void setMaxQueryRetry(int maxQueryRetry) {
        this.maxQueryRetry = maxQueryRetry;
    }

    public long getQueryDuration() {
        return queryDuration;
    }

    public void setQueryDuration(long queryDuration) {
        this.queryDuration = queryDuration;
    }

    public int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public void setMaxCancelRetry(int maxCancelRetry) {
        this.maxCancelRetry = maxCancelRetry;
    }

    public long getCancelDuration() {
        return cancelDuration;
    }

    public void setCancelDuration(long cancelDuration) {
        this.cancelDuration = cancelDuration;
    }

    @Override
    public String getId() {
        return pid + appid;
    }

    @Override
    public String toString() {
        return "AlipayMchInfo{" +
                "openApiDomain='" + openApiDomain + '\'' +
                ", mcloudApiDomain='" + mcloudApiDomain + '\'' +
                ", pid='" + pid + '\'' +
                ", appid='" + appid + '\'' +
                ", signType='" + signType + '\'' +
                ", maxQueryRetry=" + maxQueryRetry +
                ", queryDuration=" + queryDuration +
                ", maxCancelRetry=" + maxCancelRetry +
                ", cancelDuration=" + cancelDuration +
                '}';
    }

    public void validate() {
        if (StringUtils.isBlank(openApiDomain)) {
            throw new IllegalArgumentException("openApiDomain required");
        }
        if (StringUtils.isBlank(mcloudApiDomain)) {
            throw new IllegalArgumentException("mcloudApiDomain required");
        }
        if (StringUtils.isBlank(pid)) {
            throw new IllegalArgumentException("pid required");
        }
        if (StringUtils.isBlank(appid)) {
            throw new IllegalArgumentException("appid required");
        }
        if (StringUtils.isBlank(privateKey)) {
            throw new IllegalArgumentException("privateKey required");
        }
        if (StringUtils.isBlank(publicKey)) {
            throw new IllegalArgumentException("publicKey required");
        }
        if (StringUtils.isBlank(alipayPublicKey)) {
            throw new IllegalArgumentException("alipayPublicKey required");
        }
    }
}
