package com.duyongfeng;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
public class MainPane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//放选项卡的panel
	JTabbedPane tabs;
	//进行数字签名的panel
	SignPanel signPanel=new SignPanel();
	//签名验证的panel
	VerifyPanel verifyPanel=new VerifyPanel();
	//生成密钥的panel
	SecretkeyPanel secretkeyPanel=new SecretkeyPanel();
    MainPane() {
        setLayout(new BorderLayout(3,3));
        tabs = new JTabbedPane();
        tabs.addTab("获取密钥",secretkeyPanel);
        tabs.addTab("数字签名",signPanel);
        tabs.addTab("验证签名",verifyPanel);
        add(BorderLayout.CENTER, tabs);
    }
    //添加选项卡
    public Component addTab(String title, Component component) {
        return tabs.add(title, component);
    }
}
