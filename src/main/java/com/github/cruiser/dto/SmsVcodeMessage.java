package com.github.cruiser.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Qiming on 2017/2/1.
 */
@Data
@ToString
public class SmsVcodeMessage {
	private String custom_name;
	private String receive_number;

}
