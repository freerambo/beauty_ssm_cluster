package com.github.cruiser.dto.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @See http://mp.weixin.qq.com/wiki/10/79502792eef98d6e0c6e1739da387346.html
 * 例子：
 * <xml>
 * <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
 * <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
 * <CreateTime>1395658920</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
 * <MsgID>200163836</MsgID>
 * <Status><![CDATA[success]]></Status>
 * </xml>
 * Created by Qiming on 2017/2/2.
 */
@XmlRootElement(name = "xml")
public class WeixinEventMsg implements Serializable {

	private String toUserName;

	private String fromUserName;

	private int createTime;

	private String msgType;

	private String event;

	private int msgId;

	private String status;

	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@XmlElement(name = "CreateTime")
	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@XmlElement(name = "MsgID")
	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	@XmlElement(name = "Event")
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@XmlElement(name = "Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
