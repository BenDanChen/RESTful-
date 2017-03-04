package org.cc11001100.web.test.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ����һ����������Ҫ���е�����
 * 
 * @author chenjc20326
 *
 */
public class FlowDO {

	/** Ҫ�����url����  */
	private Set<String> urls;
	
	/** ȫ�ֲ��� */
	private Map<String, String> globalParams;
	
	/** ������λ�� */
	private String resultSavePath;

	public FlowDO() {
		//��ѡ�Ϊ�˱����ָ��ͳ�ʼ����
		globalParams=new HashMap<>();
	}
	
	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public String getResultSavePath() {
		return resultSavePath;
	}

	public void setResultSavePath(String resultSavePath) {
		this.resultSavePath = resultSavePath;
	}

	public Map<String, String> getGlobalParams() {
		return globalParams;
	}

	public void setGlobalParams(Map<String, String> globalParams) {
		this.globalParams = globalParams;
	}
	
}
