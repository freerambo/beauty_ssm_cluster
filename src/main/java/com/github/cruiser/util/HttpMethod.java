package com.github.cruiser.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Qiming on 2016/9/29.
 */
@Component
public class HttpMethod {

	private final Logger logger = Logger.getLogger(HttpMethod.class);

	@Value("${server.weixin.timeout}")
	private int timeout;

	@Value("${server.weixin.charset}")
	private String charset;

	@Value("${server.weixin.isproxy}")
	private String isProxy;

	@Value("${server.weixin.proxy.url}")
	private String proxyUrl;

	@Value("${server.weixin.proxy.post}")
	private int proxyPost;

	/**
	 * http 请求
	 * @param requestUrl	接口服务器URL
	 */
	public String sendHttpRequest(String requestUrl, String httpMethod){
		HttpURLConnection connect = null;
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(requestUrl);
			logger.debug("url: "+url);
			// 实例一个HTTP CONONECT
			if("true".equals(isProxy)){
				Proxy proxy = new Proxy(Proxy.Type.HTTP,
						new InetSocketAddress(proxyUrl, proxyPost));
				connect = (HttpURLConnection) url.openConnection(proxy);
			}else {
				connect = (HttpURLConnection) url.openConnection();
			}
			connect.setRequestMethod(httpMethod);
			connect.setConnectTimeout(timeout);
            logger.info("ResponseCode: "+connect.getResponseCode());
			if (connect.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new IOException(connect.getResponseMessage());
			}
			// 将返回的值存入到String中
			bufferedReader = new BufferedReader(new InputStreamReader(connect
					.getInputStream(), charset));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine())!= null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.debug(e, e);
			return "";
		}finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
				if (null != connect) {
					connect.disconnect();
				}
			}catch (IOException e){
				logger.trace(e);
			}

		}
	}

}
