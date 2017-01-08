package com.github.cruiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UtilService {

    private final Logger LOG = LoggerFactory.getLogger(UtilService.class);

    public boolean sha1CheckContent(Map<String, String> params, String sign, String charset) {
        return sha1CheckContent(getSignCheckContentV2(params), sign, charset);
    }

    /**
     * 得到待加密的明文字符串
     *
     * @param params
     * @return
     */
    public String getSignCheckContentV2(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        params.remove("sign");
        params.remove("signature");

        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.toString();
    }
    public boolean sha1CheckContent(String content, String sign, String charset) {
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(content.getBytes(charset));
            byte[] digesta = alga.digest();
            String computedSign = byte2hex(digesta);
            LOG.info("本信息摘要是 :" + computedSign);
            return sign.toLowerCase().equals(computedSign.toLowerCase());
        }catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage());
            return false;
        }
        catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    /**
     * 二行制转字符串
     *
     * @param bytes
     * @return
     */
    public String byte2hex(byte[] bytes) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < bytes.length - 1) {
                hs = hs + ":";
            }
        }
        return hs.toUpperCase();
    }
}
