package org.cc11001100.web.test.utils.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
 * �Զ����log4j����࣬���ڽ���־��Ϣ����������Ϲ��û���
 * 
 * @author chenjc20326
 *
 */
public class PanelAppender extends AppenderSkeleton {

	/** ��ǰ����̨��ʾ����Ϣ��ֱ�ӱ������ڴ��У�һ��Ҫ�ǵ�������Ȼ�͇��ˡ�����  */
	private StringBuffer mesgBuffer=new StringBuffer(30*100);
	
	/**
	 * ��ǰ������
	 */
	private Integer currentLineCount=0;

	/**
	 * �趨����̨�Ͽ�����ʾ����־�������������������ʱ�����ͷŵ�����֮ǰ�Ĳ����Ա���OOM
	 */
	private final Integer MAX_LINE_COUNT=1000;
	
	@Override
	public void close() {
		if(this.closed){
			return ;
		}
		this.closed=true;
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {
		
		//�洢������Ϣ
		String currentMesg=event.getRenderedMessage();
		System.err.println(currentMesg);
		checkMesgBuffer(currentMesg);
		
		//��MainFrame��ȡ�������������ݼ���
//		MainFrame.logDisplayTextArea.setText(mesgBuffer.toString());
		
	}
	
	/**
	 * �洢������Ϣ
	 * @param mesg
	 */
	private void checkMesgBuffer(String mesg){
		currentLineCount++;
		mesgBuffer.append(mesg);
		if(currentLineCount>=MAX_LINE_COUNT){
			int firstIndex=mesgBuffer.indexOf("\n");
			//�ҿ���һ��ÿ�
			mesgBuffer=new StringBuffer(mesgBuffer.substring(firstIndex));
		}
	}

}
