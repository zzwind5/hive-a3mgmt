package com.aerohive.nms.a3.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class A3MonitoringMessage extends A3Message {
	
	private static final long serialVersionUID = 1L;
	
	private long timeStamp;
}