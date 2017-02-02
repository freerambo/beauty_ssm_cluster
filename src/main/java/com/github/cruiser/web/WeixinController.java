package com.github.cruiser.web;

import com.github.cruiser.dto.xml.WeixinEventMsg;
import com.github.cruiser.service.UtilService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 *  负责接收微信服务器发送过来的相关请求
 */
@Controller
@RequestMapping("/wx_gateway")
public class WeixinController {

	private final Logger LOG = Logger.getLogger(this.getClass());
	@Autowired
	private UtilService utilService;
	@Value("${server.weixin.token}")
	private String token;

	@RequestMapping(value = "/sign", method = RequestMethod .GET)
	@ResponseBody
	public String confirmMsgSrc(
			@RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) {
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("signature", signature);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("nonce", nonce);
		paramsMap.put("token", token);
		LOG.debug(paramsMap.toString());
		return utilService.checkWeixinSignContent(paramsMap,signature,"UTF-8")? echostr: "";
	}

	//TODO 需要将反馈保存至数据库
	@RequestMapping(value = "/callback", method = RequestMethod .POST)
	@ResponseBody
	public String callback( @RequestBody String requestBody ) {

		LOG.info("接收微信文本消息: "+requestBody);
		WeixinEventMsg textMsg;
		try {
			JAXBContext jc = JAXBContext.newInstance(WeixinEventMsg.class);
			Unmarshaller u = jc.createUnmarshaller();
			textMsg = (WeixinEventMsg) u.unmarshal(new StreamSource(new
					StringReader(requestBody)));
		}
		catch (JAXBException e) {
			LOG.error("解析文本消息失败！");
			LOG.error(e, e);
			return "error";
		}
		LOG.info(textMsg.getToUserName());
		LOG.info(textMsg.getFromUserName());
		LOG.info(textMsg.getCreateTime());
		LOG.info(textMsg.getMsgType());
		LOG.info(textMsg.getEvent());
		LOG.info(textMsg.getMsgId());
		LOG.info(textMsg.getStatus());
	    /*Map<String, String> paramsMap = new HashMap<>();
	    paramsMap.put("signature", signature);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("nonce", nonce);
		paramsMap.put("token", token);
		LOG.debug(paramsMap.toString());
		return utilService.checkWeixinSignContent(paramsMap,signature,
		"UTF-8")? echostr: "";*/
	    return "success";
	}

}
