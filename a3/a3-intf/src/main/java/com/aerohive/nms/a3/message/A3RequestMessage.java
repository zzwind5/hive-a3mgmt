package com.aerohive.nms.a3.message;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class A3RequestMessage extends A3Message {
	
	private static final long serialVersionUID = 1L;

	private String sequenceId = UUID.randomUUID().toString();
}