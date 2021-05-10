package com.macower.core.exception;

public class BizException extends RuntimeException {

	
	private static final long serialVersionUID = -7045828468228722574L;

	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
	
	

}
