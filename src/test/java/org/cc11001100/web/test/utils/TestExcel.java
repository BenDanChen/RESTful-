package org.cc11001100.web.test.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.cc11001100.web.test.domain.ParamPair;
import org.cc11001100.web.test.domain.ResponseDO;
import org.cc11001100.web.test.utils.export.ExcelUtil;
import org.junit.Test;

/**
 * 
 * ����excel������
 * 
 * @author chenjc20326
 *
 */
public class TestExcel {

	@Test
	public void test_001() throws FileNotFoundException{
		
		ExcelUtil<ParamPair> excelUtil=new ExcelUtil<>();
		
		List<ParamPair> list=new ArrayList<>();
		list.add(new ParamPair("key","value"));
		
		excelUtil.exportExcel("����",new String[]{"header key","header value"},list, new FileOutputStream(new File("d:/test/aaa.xls")),"");
		
	}
	
}
