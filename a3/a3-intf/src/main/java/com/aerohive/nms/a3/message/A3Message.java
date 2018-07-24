package com.aerohive.nms.a3.message;

import java.io.Serializable;

import com.aerohive.nms.a3.message.config.A3HelloMessage;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * The base class of A3 message.
 * <p>
 * This class is designed to be extended. All A3 message shall extends this class or its sub-class.
 *
 * @author Jie Zhang
 */
@Data
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "msgType")  
@JsonSubTypes({
	@JsonSubTypes.Type(value=A3HelloMessage.class, name = "hello"),
	@JsonSubTypes.Type(value=A3ResponseMessage.class, name = "synResp"),
})  
public abstract class A3Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String msgType;
	
	private String sysId;
	
	private String clusterId;
}
