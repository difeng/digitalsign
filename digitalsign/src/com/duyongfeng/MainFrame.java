package com.duyongfeng;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//含有三个选项卡的panel
	MainPane mainPane=new MainPane();
	//放标题图片的panel
	TitlePane titlePane=new TitlePane();
	public MainFrame() throws HeadlessException {
		setSize(500,520);
		JPanel p=new JPanel();
		p.setLayout(null);
		titlePane.setBounds(0,0,500,100);
		mainPane.setBounds(0,100,500,400);
		p.add(titlePane);
		p.add(mainPane);
		add(p);
		JSplitPane jsp1 = new JSplitPane(0,titlePane,mainPane);
		jsp1.setDividerLocation(100);//括号里填分割线位置
		jsp1.setEnabled(false);
		add(jsp1);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("数字签名");
		setVisible(true);
	}
	public static void main(String[] args) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch(Exception e) { }
				new MainFrame();
	}
}
