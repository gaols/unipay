package com.github.gaols.unipay.api;

import com.github.gaols.unipay.utils.XmlUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class WxNotifyResponseTest {

    @Test
    public void toXml() {
        String xmlData = WxNotifyResponse.fail("fail");
        Map<String, String> map = XmlUtils.parseXml(xmlData);
        Assert.assertEquals("FAIL", map.get("return_code"));
        Assert.assertEquals("fail", map.get("return_msg"));
    }
}
