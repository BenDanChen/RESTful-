package org.cc11001100.web.test.utils.gui;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * 
 * �ļ�ѡ�񹤾��࣬���������ļ�ѡ���
 * 
 * @author chenjc20326
 *
 */
public class FileChooserUtil {

	/**
	 * ѡ���ļ���Ŀ��
	 * 
	 * @author chenjc20326
	 *
	 */
	public enum CHOOSE_TYPE{
		
		/** ��ʾ��url���� */
		OPEN_URL,
		
		/** ��ʾ��ȫ�ֲ������� */
		OPEN_GLOBAL_PARAM,
		
		/** ��ʾ���浽excel�Ĳ��� */
		SAVE_RESULT_EXCEL
		
	}
	
	/**
	 * ��һ���ļ�ѡ�񴰿�ѡ���ļ�
	 * @param chooseType
	 * @return
	 */
	public static File open(CHOOSE_TYPE chooseType){
		
		JFileChooser jFileChooser = new JFileChooser(); 
		
		// ��url�ļ�
		if(chooseType==CHOOSE_TYPE.OPEN_URL || chooseType==CHOOSE_TYPE.OPEN_GLOBAL_PARAM){
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
			jFileChooser.setCurrentDirectory(new File("d:/test/test/"));
			jFileChooser.showDialog(null,null);  
		}else if(chooseType==CHOOSE_TYPE.SAVE_RESULT_EXCEL){
			jFileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
			jFileChooser.setCurrentDirectory(new File("d:/test/test/"));
			jFileChooser.showSaveDialog(null);
		}

		return jFileChooser.getSelectedFile();  
	}
	
}
