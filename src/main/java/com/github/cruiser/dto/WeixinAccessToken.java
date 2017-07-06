package com.github.cruiser.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Qiming on 2017/2/1.
 */
@Data
@ToString(exclude = "access_token")
public class WeixinAccessToken {
	private String access_token;
	private int expires_in;

}
