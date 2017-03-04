package org.cc11001100.web.test.utils;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main_001 extends JFrame {

	public static void main(String ...args){
		
		
		Main_001 o=new Main_001();
		
		o.init();
		
		
//		JFileChooser jf = new JFileChooser();  
//		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);  
//		jf.showDialog(null,null);  
//		File fi = jf.getSelectedFile();  
//		String f = fi.getAbsolutePath()+"\\test.txt";  
//		System.out.println("save: "+f);  
//		try{  
//		    FileWriter out = new FileWriter(f);  
//		    out.write("successful!!!");  
//		    out.close();  
//		}  
//		catch(Exception e){}  
		
		
	}
	
	private void init(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setTitle("����");
		
		setLayout(new GridBagLayout());
//		addGridBagPanes();
		
		//����
		JPanel toolSelectPanel = new JPanel();
		toolSelectPanel.setBackground(Color.GREEN);
		this.add(toolSelectPanel, new GBC(0, 0, 2, 1).setFill(GBC.BOTH).setIpad(100, 100).setWeight(200, 100));
		
		//����
		toolSelectPanel = new JPanel();
		toolSelectPanel.setBackground(Color.YELLOW);
		this.add(toolSelectPanel, new GBC(2, 0).setFill(GBC.BOTH).setIpad(100,100).setWeight(100, 100));
		
		
		toolSelectPanel = new JPanel();
		toolSelectPanel.setBackground(Color.CYAN);
		this.add(toolSelectPanel, new GBC(0, 1, 3, 2).setFill(GBC.BOTH).setIpad(100,100).setWeight(300, 200));
		
		
		setVisible(true);
		
	}

	private void addGridBagPanes() {
		// �ϲ�Ĺ���ѡ�����
		JPanel toolSelectPanel = new JPanel();
		toolSelectPanel.setBackground(Color.green);
		this.add(toolSelectPanel, new GBC(0, 0, 2, 1).setFill(GBC.BOTH).setIpad(200, 50).setWeight(100, 0));
		
		// ���ľ��幤�����
		JPanel toolConcretePanel = new JPanel();
		toolConcretePanel.setBackground(Color.YELLOW);
		this.add(toolConcretePanel, new GBC(0, 1).setFill(GBC.BOTH).setIpad(70, 90).setWeight(0, 100));
		// �Ҳ�Ļ�ͼ���
		JPanel drawPanel = new JPanel();
		drawPanel.setBackground(Color.WHITE);
		this.add(drawPanel, new GBC(1, 1).setFill(GBC.BOTH));
		// �²����ɫѡ�����
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(Color.LIGHT_GRAY);
		this.add(colorPanel, new GBC(0, 2, 2, 1).setFill(GBC.BOTH).setIpad(200, 50).setWeight(100, 0));
		// �²��״̬���
		JPanel statePanel = new JPanel();
		statePanel.setBackground(Color.CYAN);
		this.add(statePanel, new GBC(0, 3, 2, 1).setFill(GBC.BOTH).setIpad(200, 20).setWeight(100, 0));
	}

	class GBC extends GridBagConstraints {
		// ��ʼ�����Ͻ�λ��
		public GBC(int gridx, int gridy) {
			this.gridx = gridx;
			this.gridy = gridy;
		}

		// ��ʼ�����Ͻ�λ�ú���ռ����������
		public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
			this.gridx = gridx;
			this.gridy = gridy;
			this.gridwidth = gridwidth;
			this.gridheight = gridheight;
		}

		// ���뷽ʽ
		public GBC setAnchor(int anchor) {
			this.anchor = anchor;
			return this;
		}

		// �Ƿ����켰���췽��
		public GBC setFill(int fill) {
			this.fill = fill;
			return this;
		}

		// x��y�����ϵ�����
		public GBC setWeight(double weightx, double weighty) {
			this.weightx = weightx;
			this.weighty = weighty;
			return this;
		}

		// �ⲿ���
		public GBC setInsets(int distance) {
			this.insets = new Insets(distance, distance, distance, distance);
			return this;
		}

		// �����
		public GBC setInsets(int top, int left, int bottom, int right) {
			this.insets = new Insets(top, left, bottom, right);
			return this;
		}

		// �����
		public GBC setIpad(int ipadx, int ipady) {
			this.ipadx = ipadx;
			this.ipady = ipady;
			return this;
		}
	}

}
