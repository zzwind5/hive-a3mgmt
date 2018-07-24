package com.aerohive.nms.a3.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class A3ResponseMessage extends A3Message {
	
	private static final long serialVersionUID = 1L;

	private String sequenceId;
	
	private boolean successful;
	
	private String errorMsg;
	
	private A3Message data;
	
	public A3ResponseMessage(A3RequestMessage requestMsg) {
		this.setSequenceId(requestMsg.getSequenceId());
		this.setAgentId(requestMsg.getAgentId());
	}
}
