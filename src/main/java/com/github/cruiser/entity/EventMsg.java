package com.github.cruiser.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.cruiser.util.CustomDateSerializer;

import java.util.Date;

/**
 * 微信事件通知
 *
 * @author Qiming Gu
 */
public class EventMsg {

	private long eventMsgId;

	private String toUserName;

	private String fromUserName;

	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;

	private String msgType;

	private String event;

	private long msgId;

	private String callbackStatus;

	private String pushStatus;

	private long orderId;

	@Override
	public String toString() {
		return "EventMsg [eventMsgId=" + eventMsgId
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName
				+ ", createTime=" + createTime + ", msgType=" + msgType
				+ ", event=" + event + ", msgId=" + msgId
				+ ", callbackStatus=" + callbackStatus
				+ ", pushStatus=" + pushStatus + ", orderId=" + orderId
				+ "]";
	}

	public long getEventMsgId() {
		return eventMsgId;
	}

	public void setEventMsgId(long eventMsgId) {
		this.eventMsgId = eventMsgId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getCallbackStatus() {
		return callbackStatus;
	}

	public void setCallbackStatus(String callbackStatus) {
		this.callbackStatus = callbackStatus;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
