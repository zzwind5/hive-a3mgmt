package com.aerohive.nms.a3.message;

import java.io.Serializable;

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
public abstract class A3Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String msgIdentifier;
	
	private String agentId;
	
	private String clusterId;
}
