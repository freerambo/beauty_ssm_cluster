package com.github.cruiser.service;

import com.slpay.common.Configure;
import com.slpay.secure.utils.KeyUtil;
import com.slpay.secure.utils.RSASignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.*;

@Service
public class UtilService {

    private final Logger LOG = LoggerFactory.getLogger(UtilService.class);

    /**
     * 检查提供的hash值和自行计算的值是否一致。
     *
     * @param params
     * @param sign
     * @param charset
     * @return
     */
    public boolean checkWeixinSignContent(Map<String, String> params, String sign, String charset) {
        return sign.toLowerCase().equals(
                getSha1Digest(getValuesContent(params), charset).toLowerCase());
    }

    /**
     * 得到待加密的明文字符串，形式如value1value2，并非key1=value1&key2=value2。
     * (按照值的字典序，而不是key名称的字典序。)
     *
     * @param params
     * @return
     */
    private String getValuesContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        params.remove("sign");
        params.remove("signature");

        StringBuffer content = new StringBuffer();
        List<String> values = new ArrayList(params.values());
        Collections.sort(values);
        for (String value : values) {
            content.append(value);
        }
        LOG.info(content.toString());
        return content.toString();
    }

    /**
     * 使用sha1进行加签，达到哈希码。
     *
     * @param content
     * @param charset 暂不支持。
     * @return
     */
    private String getSha1Digest(String content, String charset) {
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(content.getBytes("utf-8"));
            byte[] digesta = alga.digest();
            String computedSign = byte2hex(digesta);
            LOG.info("本信息摘要是 :" + computedSign);
            return computedSign;
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage());
            return "";
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return "";
        }
    }

    /**
     * 二行制转字符串
     *
     * @param bytes
     * @return
     */
    private String byte2hex(byte[] bytes) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            /*if (n < bytes.length - 1) {
                hs = hs + ":";
            }*/
        }
        return hs.toUpperCase();
    }

    //TODO 实现sha1withrsa加密
    public boolean checkShanglianSignContent(String planeText, String expectSignature, String encoding) {
        try {
            PublicKey publicKey = KeyUtil.getSlpayRsaPublicKey(Configure.getCheckSLRsaPublicKey());

            LOG.debug("Plane text: " + planeText);
            LOG.debug("Expect signature: " + expectSignature);
            return RSASignUtil.checkByRsa(planeText, expectSignature, publicKey);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    public String getKeysValuesContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        params.remove("sign");
        params.remove("signature");

        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            if (null != params.get(key) && !"".equals(params.get(key))) {
                content.append(key);
                content.append("=");
                content.append(params.get(key));
                content.append("&");
            }
        }
        String result = content.substring(0, content.length() - 1);
        LOG.info(result);
        return result;

    }
}
