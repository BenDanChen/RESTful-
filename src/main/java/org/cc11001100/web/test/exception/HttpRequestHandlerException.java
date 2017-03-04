package org.cc11001100.web.test.exception;

/**
 * �ڴ���HTTP�����ʱ����ܻ��׳����쳣
 * 
 * @author chenjc20326
 *
 */
public class HttpRequestHandlerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpRequestHandlerException() {
	}
	
	public HttpRequestHandlerException(String mesg) {
		super(mesg);
	}
	
	public HttpRequestHandlerException(Throwable cause) {
		super(cause);
	}
	
	public HttpRequestHandlerException(String mesg, Throwable cause) {
		super(mesg, cause);
	}
	
}
