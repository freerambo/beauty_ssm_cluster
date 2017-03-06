package com.github.cruiser.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.BizException;
import okhttp3.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Qiming on 2017/2/1.
 *
 * @from http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html
 */
@Component
public class OkHttpUtil {
	private static final Logger logger = Logger.getLogger(OkHttpUtil.class);

	private static int timeout;

	private static String CHARSET_NAME;

	private static String isProxy;

	private static String proxyUrl;

	private static int proxyPost;

	@Value("${server.weixin.timeout}")
	public static void setTimeout(int timeout) {
		OkHttpUtil.timeout = timeout;
	}

	@Value("${server.weixin.charset_name}")
	public static void setCharsetName(String charsetName) {
		OkHttpUtil.CHARSET_NAME = charsetName;
	}

	@Value("${server.weixin.isproxy}")
	public static void setIsProxy(String isProxy) {
		OkHttpUtil.isProxy = isProxy;
	}

	@Value("${server.weixin.proxy.url}")
	public static void setProxyUrl(String proxyUrl) {
		OkHttpUtil.proxyUrl = proxyUrl;
	}

	@Value("${server.weixin.proxy.post}")
	public static void setProxyPost(int proxyPost) {
		OkHttpUtil.proxyPost = proxyPost;
	}

	private static final OkHttpClient mOkHttpClient =
			new OkHttpClient().newBuilder()
					.readTimeout(timeout, TimeUnit.SECONDS)
					.build();

	/**
	 * 同步发送HTTP请求。
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Response execute(Request request) throws IOException {
		logger.info("同步发送http请求.");
		logger.debug("heads: " + request.headers());
		logger.debug("body: " + request.body());
		return mOkHttpClient.newCall(request).execute();
	}

	/**
	 * 异步发送HTTP请求，并通过回调函数对返回进行处理.
	 *
	 * @param request
	 * @param responseCallback
	 */
	public static void enqueue(Request request, Callback responseCallback) {
		logger.info("异步发送http请求.");
		logger.debug("heads: " + request.headers());
		logger.debug("body: " + request.body());
		mOkHttpClient.newCall(request).enqueue(responseCallback);
	}

	/**
	 * 异步发送HTTP请求，并通过回调函数对返回进行处理.
	 * 回调函数只记log
	 *
	 * @param request
	 */
	public static void enqueue(Request request) {
		logger.info("异步发送http请求.");
		logger.debug("heads: " + request.headers());
		logger.debug("body: " + request.body());
		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				logger.info("异步发送http请求失败！！");
				logger.info(e, e);
			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				logger.info("异步发送http请求成功！！");
				logger.debug("response: " + response.body().string());

			}
		});
	}

	/**
	 * 可以支持post put patch等其他方法的请求。
	 * @param request
	 * @return
	 */
	public static String getStringFromServer(Request request){
		Response response = null;
		try {
			response = execute(request);
		}
		catch (IOException e) {
			logger.error(e, e);
			throw new BizException(ResultEnum.HTTP_IO_ERROR.getResultMsg(), e);
		}

		if (response.isSuccessful()) {
			String responseBody = null;
			try {
				responseBody = response.body().string();
			}
			catch (IOException e) {
				logger.error(e, e);
				throw new BizException(ResultEnum.HTTP_RESPONSE_ERROR.getResultMsg(), e);
			}
			return responseBody;
		} else {
			logger.error("Unexpected code " + response);
			throw new BizException(ResultEnum.HTTP_RESPONSE_ERROR.getResultMsg());
		}
	}

	/**
	 * 通过GET方式直接获取返回字段
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getStringFromServer(String url){
		Request request = new Request.Builder().url(url).build();
		return getStringFromServer(request);
	}

	/**
	 * 格式化使用了Apache HttpComponents Client
	 * @See https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.0-alpha4
	 * @param params
	 * @return
	 */
	public static String formatParams(List<NameValuePair> params) {
		return URLEncodedUtils.format(params, CHARSET_NAME);
	}

	/**
	 * 为已生成的 url query string后面再添加一个或多个name value 参数。
	 * @param url
	 * @param params
	 * @return
	 */
	public static String attachHttpGetParams(String url,
			List<NameValuePair> params) {
		return url + "?" + formatParams(params);
	}

	/**
	 * 为HttpGet 的 url 方便的添加1个name value 参数。
	 * @param url
	 * @param name
	 * @param value
	 * @return
	 */
	public static String attachHttpGetParam(String url, String name,
			String value) {
		return url.contains("?")? url + "&" + name + "=" + value
				: url + "?" + name + "=" + value;
	}
}
