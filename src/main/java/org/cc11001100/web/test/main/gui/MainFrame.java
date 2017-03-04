package org.cc11001100.web.test.main.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.StringUtils;
import org.cc11001100.web.test.domain.FlowDO;
import org.cc11001100.web.test.domain.ResponseDO;
import org.cc11001100.web.test.utils.InputUtil;
import org.cc11001100.web.test.utils.UrlScheduler;
import org.cc11001100.web.test.utils.export.ExportUtils;
import org.cc11001100.web.test.utils.gui.FileChooserUtil;
import org.cc11001100.web.test.utils.gui.FileChooserUtil.CHOOSE_TYPE;

/**
 * ������
 * 
 * @author chenjc20326
 *
 */
public class MainFrame extends JFrame {

	/** ����һ��ɨ����Ҫ�Ķ������������� */
	private FlowDO flowDO;
	
	/** �����������ĵط� */
	private JTextField hostTextField;
	/** ����˿ںŵĵط� */
	private JTextField postTextField;
	
	/**
	 * ��ʾ��־�ĵط�
	 */
	public static JTextArea logDisplayTextArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					
					// ������
//					try {
//						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//							| UnsupportedLookAndFeelException e) {
//						e.printStackTrace();
//					}
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		flowDO=new FlowDO();
		
		setFont(new Font("Courier New", Font.PLAIN, 12));
		setTitle("\u63A5\u53E3\u54CD\u5E94\u901F\u5EA6\u6279\u91CF\u6D4B\u8BD5\u5DE5\u5177 v0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u6253\u5F00\u6587\u4EF6");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u4FDD\u5B58\u6587\u4EF6");
		mnNewMenu.add(menuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem menuItem = new JMenuItem("\u5173\u95ED\u7A0B\u5E8F");
		mnNewMenu.add(menuItem);
		
		JMenu menu = new JMenu("\u7F16\u8F91");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u7F16\u8F91\u53C2\u6570");
		menu.add(menuItem_2);
		
		JMenu menu_1 = new JMenu("\u67E5\u770B");
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u5E2E\u52A9\u6587\u6863");
		menu_2.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5173\u4E8E\u4F5C\u8005");
		menu_2.add(menuItem_4);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		final JButton btnNewButton = new JButton("\u9009\u62E9url\u6587\u4EF6");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//ѡ��url�ļ�
				File file=FileChooserUtil.open(CHOOSE_TYPE.OPEN_URL);
				
				// δѡ���ļ�
				if(file==null){
					return ;
				}
				
				//���������Ȼ����
				Set<String> urls=InputUtil.readUrlFromFile(file);
				MainFrame.this.flowDO.setUrls(urls);
				
				//�ı����ָ�����
				StringBuilder sb=new StringBuilder();
				sb.append("URL:").append(file.getPath());
				btnNewButton.setText(sb.toString());
				
			}
		});
		btnNewButton.setBounds(40, 30, 533, 44);
		mainPanel.add(btnNewButton);
		
		final JButton btnNewButton_1 = new JButton("\u9009\u62E9\u5168\u5C40\u53C2\u6570\u6587\u4EF6");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ѡ��ȫ�ֲ����ļ�
				File file=FileChooserUtil.open(CHOOSE_TYPE.OPEN_GLOBAL_PARAM);
				
				// δѡ���ļ�
				if(file==null){
					return ;
				}
				
				//���������Ȼ����
				Set<String> lines=InputUtil.readUrlFromFile(file);
				Map<String,String> globalParams=InputUtil.linesToParamMap(lines);
				MainFrame.this.flowDO.setGlobalParams(globalParams);
				
				//�ı����ָ�����
				StringBuilder sb=new StringBuilder();
				sb.append("PARAMS:").append(file.getPath());
				btnNewButton_1.setText(sb.toString());
				
			}
		});
		btnNewButton_1.setBounds(40, 91, 533, 44);
		mainPanel.add(btnNewButton_1);
		
		final JButton chooseSavePathBtn = new JButton("\u9009\u62E9\u7ED3\u679C\u8F93\u51FA\u6587\u4EF6");
		chooseSavePathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ѡ��������ļ�
				File file=FileChooserUtil.open(CHOOSE_TYPE.SAVE_RESULT_EXCEL);
				
				// δѡ���ļ�
				if(file==null){
					return ;
				}
				
				//���������Ȼ����
				String resultSavePath=file.getPath();
				MainFrame.this.flowDO.setResultSavePath(resultSavePath);
				
				//�ı����ָ�����
				StringBuilder sb=new StringBuilder();
				sb.append("SAVE:").append(resultSavePath);
				chooseSavePathBtn.setText(sb.toString());
				
			}
		});
		chooseSavePathBtn.setBounds(40, 145, 533, 44);
		mainPanel.add(chooseSavePathBtn);
		
		final JButton lauchBtn = new JButton("\u5F00\u59CB");
		lauchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// ��ʼ��ť
				final FlowDO flowDO=MainFrame.this.flowDO;
				
				//����Ƿ��Ѿ���������
				if(!checkFlowDO(flowDO)){
					return;
				}
				
				final String host=hostTextField.getText().trim();
				final String post=postTextField.getText().trim();
				
				lauchBtn.setText("����ɨ��...");
				
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						List<ResponseDO> list=UrlScheduler.stupidBlockQueueScheduler(host,post,flowDO.getUrls(), flowDO.getGlobalParams());
						Collections.sort(list);
						ExportUtils.export2Excel(list, flowDO.getResultSavePath());
						
					}
				}).start();
				
				lauchBtn.setText("��ʼ");
				
			}
		});
		lauchBtn.setBounds(633, 148, 105, 39);
		mainPanel.add(lauchBtn);
		
		hostTextField = new JTextField();
		hostTextField.setText("http://localhost");
		hostTextField.setBounds(610, 42, 128, 21);
		mainPanel.add(hostTextField);
		hostTextField.setColumns(10);
		
		postTextField = new JTextField();
		postTextField.setText("8080");
		postTextField.setBounds(610, 91, 128, 21);
		mainPanel.add(postTextField);
		postTextField.setColumns(10);
		
		JScrollPane logConsolePane = new JScrollPane();
		logConsolePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		logConsolePane.setBounds(10, 216, 764, 315);
		mainPanel.add(logConsolePane);
		
		logDisplayTextArea = new JTextArea("New label");
		logDisplayTextArea.setLineWrap(true);
		logConsolePane.setViewportView(logDisplayTextArea);
		logConsolePane.setWheelScrollingEnabled(true);
		for(int i=0;i<1000;i++){
			String oldMesg=logDisplayTextArea.getText();
			oldMesg+="һ�仰\n"+i;
			logDisplayTextArea.setText(oldMesg);
		}

	}
	
	/**
	 * �Ա���������һ�����
	 * @param flowDO
	 * @return
	 */
	private boolean checkFlowDO(FlowDO flowDO){
		
		//���url����
		Set<String> urls=flowDO.getUrls();
		if(urls==null || urls.isEmpty()){
			JOptionPane.showMessageDialog(null, "����ѡ��URL!", "ȱ��URL", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		//���ȫ�ֲ��� ��ѡ��ü��
//		Map<String,String> globalParams=flowDO.getGlobalParams();
//		if(globalParams==null || globalParams.isEmpty()){
//			
//			return false;
//		}
		
		String savePath=flowDO.getResultSavePath();
		if(StringUtils.isEmpty(savePath)){
			JOptionPane.showMessageDialog(null, "��ѡ��ɨ��������·��URL!", "��ѡ��ɨ��������·��", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
}
