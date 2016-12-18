package com.github.cruiser.entity;

/**
 * 路由记录
 * @author Qiming Gu
 *
 */
public class Route {
	
	private long routeId;
	
	private long merchantId;
	
	private String weixinRoute;
	
	private String alipayRoute;

	private long priority;

	@Override
	public String toString() {
		return "Route [routeId=" + routeId
				+ ", merchantId=" + merchantId
				+ ", weixinRoute=" + weixinRoute
				+ ", alipayRoute=" + alipayRoute
				+ ", priority=" + priority
				+ "]";
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public String getWeixinRoute() {
		return weixinRoute;
	}

	public void setWeixinRoute(String weixinRoute) {
		this.weixinRoute = weixinRoute;
	}

	public String getAlipayRoute() {
		return alipayRoute;
	}

	public void setAlipayRoute(String alipayRoute) {
		this.alipayRoute = alipayRoute;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}
}
