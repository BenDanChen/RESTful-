package org.cc11001100.web.test.process;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.cc11001100.web.test.domain.ParamPair;
import org.cc11001100.web.test.domain.ResponseDO;
import org.cc11001100.web.test.generator.ParamGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * ����jetty�������Ĳ���ȱʧ�Ĵ���
 * 
 * ���jetty�����eclipse intergration�汾�ģ��������������ԣ������ڷ��������ض����󷵻ء�
 * 
 *  �������Ĺ���ԭ����jetty�������Ὣ����ȱʧ��Ϣ��Ϊ400��ͬʱ�᷵��һ��400ҳ�棨html����ʾ�û���
 *  ���������������ǽ������400ҳ�棬��������Ҫ�Ĳ������ֺͲ�������ȡ������
 *  
 * 
 * @author chenjc20326
 *
 */
public class ParamLackProcessor4Jetty implements ParamLackProcessor{

	private Logger logger=Logger.getLogger(ParamLackProcessor4Jetty.class);
	
	/** html��title��ǩ������ */
	private static final String TITLE_TAG_NAME="title";
	
	/** ���� */
	private static final String QUOTATION_MARK="'";
	
	/** ������ */
	private static final String BRACKET="[]";
	
	/** ���ַ��� */
	private static final String NULL_STRING="";
	
	@Override
	public ParamPair process(ResponseDO responseDO) {

if("http://localhost:8080/busi/contract/replaceReceive.json".equals(responseDO.getUrl())){
	System.out.println("stop");
}		
		
		String resposneContent=responseDO.getResponseContent();
		
		Document document=Jsoup.parse(resposneContent);
		Elements elements=document.getElementsByTag(TITLE_TAG_NAME);
		Element titleElement=elements.get(0);
		
		if(titleElement==null){
			return null;
		}
		
		String titleContent=titleElement.text();
		
		Class<? extends Object> clazz=getTypeByTitleContent(titleContent);
		if(clazz==null){
			return null;
		}
		
		int beginIndex=titleContent.indexOf(QUOTATION_MARK)+1;
		int endIndex=titleContent.lastIndexOf(QUOTATION_MARK);
		if(beginIndex<0 || endIndex<0){
			return null;
		}
		
		String paramName=titleContent.substring(beginIndex, endIndex);
		String paramValue=ParamGenerator.generatorStringParamValue(clazz, paramName);
		
		return new ParamPair(paramName, paramValue);
	}

	@Override
	public boolean canProcess(ResponseDO responseDO) {
		return true;
	}
	
	/**
	 * ���ݴ���ı�����������������������Ҫ�Ĳ�����ʲô���͵�
	 * @param titleContent
	 * @return
	 */
	private Class<? extends Object> getTypeByTitleContent(String titleContent){
		logger.info(titleContent);		
		titleContent=titleContent.substring("Error 400 Required ".length());
		String className=titleContent.split("\\s+")[0].replace(BRACKET,NULL_STRING);
		
		try {
			return Class.forName("java.lang."+StringUtils.capitalize(className));
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
		return null;
	}

}
