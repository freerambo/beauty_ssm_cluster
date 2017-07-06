package com.github.cruiser.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * Created by Qiming on 2017/2/1.
 */
@Data
@ToString
public class WeixinTemplate {
	private String touser;
	private String template_id;
	private String url;
	private String topcolor;
	private Map<String, WeixinTemplateStringPair> data;

}

