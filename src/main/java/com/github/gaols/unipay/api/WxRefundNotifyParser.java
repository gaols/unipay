package com.github.gaols.unipay.api;

import com.github.gaols.unipay.utils.ParaUtils;
import com.github.gaols.unipay.utils.XmlUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;

public class WxRefundNotifyParser implements RefundNotifyParser {

    private Map<String, String> paras;

    @Override
    public boolean isSuccess() {
        Map<String, String> parasMap = getNotifyParasMap();
        String refundStatus = parasMap.get("refund_status");
        return "SUCCESS".equalsIgnoreCase(refundStatus);
    }

    public String getOutRefundNo() {
        Map<String, String> parasMap = getNotifyParasMap();
        return parasMap.get("out_refund_no");
    }

    @Override
    public Map<String, String> getNotifyParasMap() {
        return this.paras;
    }

    public WxRefundNotifyParser(HttpServletRequest request, String mchKey) {
        try {
            Map<String, String> map = XmlUtils.parseXml(request.getInputStream());
            String returnCode = map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String reqInfo = map.get("req_info");
                if (StringUtils.isNotBlank(reqInfo)) {
                    Map<String, String> xmlMap = decryptReqInfo(mchKey, reqInfo);
                    map.putAll(xmlMap);
                    ParaUtils.formatParas(xmlMap, "\nWeixin refund:");
                }
            }

            this.paras = Collections.unmodifiableMap(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> decryptReqInfo(String mchKey, String reqInfoStr) {
        try {
            final String keyMd5String = DigestUtils.md5Hex(mchKey).toLowerCase();
            SecretKeySpec key = new SecretKeySpec(keyMd5String.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            String xmlData = new String(cipher.doFinal(Base64.decodeBase64(reqInfoStr)));
            return XmlUtils.parseXml(xmlData);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
