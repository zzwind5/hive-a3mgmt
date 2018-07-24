package com.aerohive.nms.a3.message.config;

import com.aerohive.nms.a3.message.A3RequestMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class A3HelloMessage extends A3RequestMessage {
	
	private static final long serialVersionUID = 1L;
	
	private String desc;

}
