package com.github.cruiser.web;

import com.github.cruiser.entity.Route;
import com.github.cruiser.service.RoutesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/wx_gateway")
public class WeixinController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	//@Autowired
	//private RoutesService routesService;

	@RequestMapping(value = "/sign", method = RequestMethod
			.GET)
	@ResponseBody
	public String confirmMsgSrc(
			@RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) {
		LOG.info("sign: ");
		LOG.info("-------------------------------");
		return echostr;
	}

}
