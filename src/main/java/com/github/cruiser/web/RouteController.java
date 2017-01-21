package com.github.cruiser.web;

import com.github.cruiser.service.RoutesService;
import com.github.cruiser.entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/route")
public class RouteController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoutesService routesService;

	@RequestMapping(value = "/merchant_id/{merchantId}", method = RequestMethod
			.GET)
	public String getById(@RequestHeader Map<String, String> heads,
			@PathVariable("merchantId") long merchantId) {
		LOG.info("heads: ");
		LOG.info("-------------------------------");
		LOG.info(heads.toString());
		LOG.info("-------------------------------");
		Route route = routesService.getHightestPriorityById(merchantId);
		LOG.info("route: ");
		LOG.info("-------------------------------");
		LOG.info(null==route?"路由记录为空":route.toString());
		LOG.info("-------------------------------");

		String result = "";
		String userAgent = heads.get("user-agent");
		if (userAgent.toLowerCase().contains("MicroMessenger".toLowerCase())) {
			result = "redirect:" + route.getWeixinRoute();
		}
		else if (userAgent.toLowerCase()
				.contains("AlipayClient".toLowerCase())) {
			result = "redirect:" + route.getAlipayRoute();
		}
		else {
			//TODO 404的页面其实还没有做
			//result = "redirect:" + route.getAlipayRoute();
			result = "404";
		}
		return result;
	}

	/**
	 * 摒弃jsp页面通过ajax接口做到真正意义上的前后分离
	 */
	@RequestMapping(value = "/merchant_code/{merchantCode}", method =
			RequestMethod .GET)
	public String getByCode(@RequestHeader Map<String, String> heads,
			@PathVariable("merchantCode") String merchantCode) {
		LOG.info("heads: ");
		LOG.info("-------------------------------");
		LOG.info(heads.toString());
		LOG.info("-------------------------------");
		Route route = routesService.getHightestPriorityByCode(merchantCode);
		LOG.info("route: ");
		LOG.info("-------------------------------");
		LOG.info(null==route?"路由记录为空":route.toString());
		LOG.info("-------------------------------");

		String result = "";
		String userAgent = heads.get("user-agent");
		if (userAgent.toLowerCase().contains("MicroMessenger".toLowerCase())) {
			result = "redirect:" + route.getWeixinRoute();
		}
		else if (userAgent.toLowerCase()
				.contains("AlipayClient".toLowerCase())) {
			result = "redirect:" + route.getAlipayRoute();
		}
		else {
			//TODO
			//result = "redirect:" + route.getAlipayRoute();
			result = "404";
		}
		return result;
	}

}
