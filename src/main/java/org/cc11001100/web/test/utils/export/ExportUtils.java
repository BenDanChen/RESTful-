package org.cc11001100.web.test.utils.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.cc11001100.web.test.domain.ResponseDO;

/**
 * ��������
 * 
 * 
 * @author chenjc20326
 *
 */
public class ExportUtils {

	private static Logger logger=Logger.getLogger(ExportUtils.class);
	
	/**
	 * ���������Ϊexcel���
	 * @param list
	 * @param path
	 */
	public static void export2Excel(List<ResponseDO> list, String path){
		 // ����һ��������
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // ����һ�����
	      HSSFSheet sheet = workbook.createSheet("ɨ����");
	      // ���ñ��Ĭ���п��Ϊ15���ֽ�
	      sheet.setDefaultColumnWidth((short) 15);
	      // ����һ����ʽ
	      HSSFCellStyle style = workbook.createCellStyle();
	      // ������Щ��ʽ
	      style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      
	      // ����һ������
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.VIOLET.index);
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // ������Ӧ�õ���ǰ����ʽ
	      style.setFont(font);
	      
	      // ���ɲ�������һ����ʽ
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      
	      // ������һ������
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // ������Ӧ�õ���ǰ����ʽ
	      style2.setFont(font2);
	     
	      // ����һ����ͼ�Ķ���������
	      HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	      // ����ע�͵Ĵ�С��λ��,����ĵ�
//	      HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
//	      // ����ע������
//	      comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));
//	      // ����ע�����ߣ�������ƶ�����Ԫ�����ǿ�����״̬���п���������.
//	      comment.setAuthor("CC11001100");
	 
	      
	      String[] headers=new String[]{"��Ӧʱ��(ms)", "��Ӧ��", "����url","��������"};
	      //������������
	      HSSFRow row = sheet.createRow(0);
	      for (short i = 0; i < headers.length; i++) {
	         HSSFCell cell = row.createCell(i);
	         cell.setCellStyle(style);
	         HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	         cell.setCellValue(text);
	      }
	 
	      
	      for(int i=0;i<list.size();i++){
	    	  
	    	  ResponseDO responseDO=list.get(i);
	    	  row = sheet.createRow(i+1);
	    	  
	    	  //��Ӧʱ��
	    	  HSSFCell cell = row.createCell(0);
	    	  cell.setCellStyle(style2);
	    	  HSSFRichTextString richString = new HSSFRichTextString(responseDO.getSpend().toString());
              HSSFFont font3 = workbook.createFont();
              font3.setColor(HSSFColor.BLUE.index);
              richString.applyFont(font3);
              cell.setCellValue(richString);
              sheet.setColumnWidth(0, 15*255);
              
              //��Ӧ��
	    	  cell = row.createCell(1);
	    	  cell.setCellStyle(style2);
	    	  richString = new HSSFRichTextString(responseDO.getStatus().toString());
              font3 = workbook.createFont();
              font3.setColor(HSSFColor.BLUE.index);
              richString.applyFont(font3);
              cell.setCellValue(richString);
              sheet.setColumnWidth(1, 15*255);
              
              //����url
	    	  cell = row.createCell(2);
	    	  cell.setCellStyle(style2);
	    	  StringBuilder url=new StringBuilder(responseDO.getUrl());
	    	  Map<String, String> params=responseDO.getParams();
	    	  boolean isFirst=true;
	    	  for(Iterator<Entry<String,String>> iterator=params.entrySet().iterator();iterator.hasNext();){
	    		  Entry<String,String> entry=iterator.next();
	    		  StringBuilder sb=new StringBuilder();
	    		  sb.append(entry.getKey()).append("=").append(entry.getValue());
	    		  if(isFirst){
	    			  url.append("?");
	    			  isFirst=false;
	    		  }else{
	    			  url.append("&");
	    		  }
	    		  url.append(sb.toString());
	    	  }
	    	  richString = new HSSFRichTextString(url.toString());
              font3 = workbook.createFont();
              font3.setColor(HSSFColor.BLUE.index);
              richString.applyFont(font3);
              cell.setCellValue(richString);
              sheet.setColumnWidth(2, 78*255);
              
              //��������
	    	  cell = row.createCell(3);
	    	  cell.setCellStyle(style2);
	    	  String responseContent=responseDO.getResponseContent();
	    	  if(responseContent.length()>32765){
	    		  responseContent=responseContent.substring(0,32700);
	    		  responseContent+="(AFTER DATA BECAUSE TOO LONG CANT STORE!!!)";
	    	  }
	    	  richString = new HSSFRichTextString(responseContent);
              font3 = workbook.createFont();
              font3.setColor(HSSFColor.BLUE.index);
              richString.applyFont(font3);
              cell.setCellValue(richString);
              sheet.setColumnWidth(3,78*255);
              
              
              logger.info("Export ["+responseDO.getUrl()+"] to excel.");
              
	      }
	      
	      logger.info("Export all urls to excel done.");
//	      
//	      //�����������ݣ�����������
//	      Iterator<T> it = list.iterator();
//	      int index = 0;
//	      while (it.hasNext()) {
//	         index++;
//	         row = sheet.createRow(index);
//	         T t = (T) it.next();
//	         //���÷��䣬����javabean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ
//	         Field[] fields = t.getClass().getDeclaredFields();
//	         for (short i = 0; i < fields.length; i++) {
//	            HSSFCell cell = row.createCell(i);
//	            cell.setCellStyle(style2);
//	            Field field = fields[i];
//	            String fieldName = field.getName();
//	            String getMethodName = "get"
//	                   + fieldName.substring(0, 1).toUpperCase()
//	                   + fieldName.substring(1);
//	            try {
//	                Class tCls = t.getClass();
//	                Method getMethod = tCls.getMethod(getMethodName,
//	                      new Class[] {});
//	                Object value = getMethod.invoke(t, new Object[] {});
//	                //�ж�ֵ�����ͺ����ǿ������ת��
//	                String textValue = null;
//	                //�������ͼƬ���ݣ�������������ʽ�ж�textValue�Ƿ�ȫ�����������
//	                if(textValue!=null){
//	                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
//	                    HSSFFont font3 = workbook.createFont();
//	                    font3.setColor(HSSFColor.BLUE.index);
//	                    richString.applyFont(font3);
//	                    cell.setCellValue(richString);
//	                }
//	            } catch (SecurityException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            } catch (NoSuchMethodException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            } catch (IllegalArgumentException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            } catch (IllegalAccessException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            } catch (InvocationTargetException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            } finally {
//	                //������Դ
//	            }
//	         }
//	 
//	      }
	      try {
	         workbook.write(new FileOutputStream(new File(path)));
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	 
	}
	
}
