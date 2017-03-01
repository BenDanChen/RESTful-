package org.cc11001100.web.test.process;

import org.cc11001100.web.test.domain.ParamPair;
import org.cc11001100.web.test.domain.ResponseDO;

/**
 * ��������ʱ����ȱʧ����������չ����ӿ�֧�ָ��������
 * 
 * @author chenjc20326
 *
 */
public interface ParamLackProcessor {
	
	/**
	 * ���Ƿ��ܹ��������������
	 * 
	 * @param responseDO
	 * @return
	 */
	boolean canProcess(ResponseDO responseDO);
	
	/**
	 * ����һ������ʧ�ܵķ��ش�������Ȼ����д���õ�ȱʧ�Ĳ���
	 * 
	 * @param responseDO ��һ�ν�������õ��Ľ��
	 * @return ȱʧ�Ĳ�������
	 */
	ParamPair process(ResponseDO responseDO);
	
}
