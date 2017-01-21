package com.github.cruiser.web;

import com.github.cruiser.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wx_gateway")
public class WeixinController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UtilService utilService;
	@Value("${server.weixin.token}")
	private String token;

	@RequestMapping(value = "/sign", method = RequestMethod
			.GET)
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

}
