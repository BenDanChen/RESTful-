package org.cc11001100.web.test.generator;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * ���������������ݴ���Ĳ��������ɶ�Ӧ��ֵ
 * 
 * 
 * Ϊʲô���������
 * 		������ȷ�Ĳ���ֵ����߲��Եľ�׼�ȣ����и��õİ취��  = = 		
 * 
 * 
 * @author chenjc20326
 *
 */
public class ParamGenerator {
	
	/**
	 * ���ݴ���Ĳ������ɶ�Ӧ��ֵ�������ɵĲ���ֵͳͳתΪString���͵ģ���Ϊ�������ϴ���Ĳ������Ͷ���String���͵�
	 * 
	 * @param <T>
	 * 
	 * @param paramName
	 * @return
	 */
	public static <T> String generatorStringParamValue(Class<T> clazz, String paramName){

		if(StringUtils.isEmpty(paramName)){
			return null;
		}
		paramName=paramName.toLowerCase();
		
		if(clazz==String.class){
			return processString(paramName);
		}
		
		if(clazz==Integer.class){
			return processInteger(paramName);
		}
		
		if(clazz==Date.class){
			return processDate(paramName);
		}
		
		if(clazz==Long.class){
			return processLong(paramName);
		}
		
		if(clazz==Double.class){
			return processDouble(paramName);
		}
		
		if(clazz==Float.class){
			return processFloat(paramName);
		}
		
		if(clazz==Boolean.class){
			return processBoolean(paramName);
		}
		
		if(clazz==Short.class){
			return processShort(paramName);
		}
		
		if(clazz==Byte.class){
			return processByte(paramName);
		}
		
		if(clazz==Character.class){
			return processCharacter(paramName);
		}
		
		return null;
	}
	
	/**
	 * ���������������͵�����
	 * @param paramName
	 * @return
	 */
	private static String processDate(String paramName) {
		
		DateTime datetime=new DateTime();
		// ����һ������֮ǰ�ܾõ�����  ��������������б��ѯ��������ʼ����
		if(paramName.contains("start") || paramName.contains("begin")){
			datetime.minusMonths(new Random().nextInt(5)+1);
		}else {
			//����һ������֮������ڣ���������������б��ѯ�����Ľ�������
			datetime.plusMonths(new Random().nextInt(5)+1);
		}
		
		return datetime.toString("yyyy-MM-dd HH:mm:ss");
	}

	/** String����Ĭ�ϵķ���ֵ */
	private static final String DEFAULT_STRING="fooooobar";
	
	/**
	 * �ڲ�������String���͵Ĵ���
	 * 
	 * @param paramName
	 * @return
	 */
	private static String processString(String paramName){
		
		//�жϿ�����String���͵�Date
		if(paramName.contains("time") || paramName.contains("date") || paramName.contains("datetime")){
			return processDate(paramName);
		}
		
		//������String���͵�Integer
		if(paramName.contains("num") || paramName.contains("number") || paramName.contains("count") || paramName.contains("id")){
			return processInteger(paramName);
		}
		
		return DEFAULT_STRING;
	}
	
	/**
	 * ��Integer���͵Ĳ���������
	 * 
	 * @param paramName
	 * @return
	 */
	public static String processInteger(String paramName){
		Integer res=new Random().nextInt(Integer.MAX_VALUE);
		return res.toString();
	}
	
	/**
	 * ��Long���͵Ĳ���������
	 * 
	 * @param paramName
	 * @return
	 */
	public static String processLong(String paramName){
		return processInteger(paramName);
	}
	
	/**
	 * ��Character���͵Ĳ���������
	 * @param paramName
	 * @return
	 */
	private static String processCharacter(String paramName) {
		Character c=DEFAULT_STRING.charAt(new Random().nextInt(DEFAULT_STRING.length()));
		return c.toString();
	}

	/**
	 * ��Byte����������
	 * @param paramName
	 * @return
	 */
	private static String processByte(String paramName) {
		return new Byte("1").toString();
	}

	/**
	 * ��Short���͵�����������
	 * @param paramName
	 * @return
	 */
	private static String processShort(String paramName) {
		return "17";
	}

	/**
	 * ��Boolean���͵�����������
	 * @param paramName
	 * @return
	 */
	private static String processBoolean(String paramName) {
		Boolean res=new Random().nextBoolean();
		return res.toString();
	}

	/**
	 * ��Float���͵�����������
	 * @param paramName
	 * @return
	 */
	private static String processFloat(String paramName) {
		Float res=new Random().nextFloat();
		return res.toString();
	}

	/**
	 * ��Double���͵�����������
	 * @param paramName
	 * @return
	 */
	private static String processDouble(String paramName) {
		Double res=new Random().nextDouble();
		return res.toString();
	}

}
