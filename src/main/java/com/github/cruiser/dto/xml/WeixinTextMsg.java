package com.github.cruiser.dto.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @See http://mp.weixin.qq.com/wiki/10/79502792eef98d6e0c6e1739da387346.html
 * 例子：
 *  <xml>
 *      <ToUserName><![CDATA[toUser]]></ToUserName>
 *      <FromUserName><![CDATA[fromUser]]></FromUserName>
 *      <CreateTime>1348831860</CreateTime>
 *      <MsgType><![CDATA[text]]></MsgType>
 *      <Content><![CDATA[this is a test]]></Content>
 *      <MsgId>1234567890123456</MsgId>
 *  </xml>
 * Created by Qiming on 2017/2/2.
 */
@XmlRootElement(name = "xml")
public class WeixinTextMsg implements Serializable{

	private	String toUserName;
	private String fromUserName;
	private int createTime;
	private String msgType;
	private String content;
	private String msgId;

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

	@XmlElement(name = "Content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlElement(name = "MsgId")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
