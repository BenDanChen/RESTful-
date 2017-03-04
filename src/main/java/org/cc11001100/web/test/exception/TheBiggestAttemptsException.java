package org.cc11001100.web.test.exception;

/**
 * 
 * �����趨������Դ�����ʱ����׳����쳣
 * 
 * @author chenjc20326
 *
 */
public class TheBiggestAttemptsException extends HttpRequestHandlerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TheBiggestAttemptsException() {
	}
	
	public TheBiggestAttemptsException(String mesg) {
		super(mesg);
	}
	
	public TheBiggestAttemptsException(Throwable cause) {
		super(cause);
	}
	
	public TheBiggestAttemptsException(String mesg, Throwable cause) {
		super(mesg, cause);
	}
	
}
