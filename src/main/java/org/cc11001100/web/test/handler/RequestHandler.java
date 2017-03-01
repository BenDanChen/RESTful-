package org.cc11001100.web.test.handler;

import java.util.Map;

import org.cc11001100.web.test.domain.ResponseDO;

/**
 * 
 * Ҫ��һ��������
 * 
 * @author chenjc20326
 *
 */
public interface RequestHandler {

	/**
	 * ��������һ������
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	ResponseDO handle(String url, Map<String,String> params);
	
}
