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
	//��ѡ���panel
	JTabbedPane tabs;
	//��������ǩ����panel
	SignPanel signPanel=new SignPanel();
	//ǩ����֤��panel
	VerifyPanel verifyPanel=new VerifyPanel();
	//������Կ��panel
	SecretkeyPanel secretkeyPanel=new SecretkeyPanel();
    MainPane() {
        setLayout(new BorderLayout(3,3));
        tabs = new JTabbedPane();
        tabs.addTab("��ȡ��Կ",secretkeyPanel);
        tabs.addTab("����ǩ��",signPanel);
        tabs.addTab("��֤ǩ��",verifyPanel);
        add(BorderLayout.CENTER, tabs);
    }
    //���ѡ�
    public Component addTab(String title, Component component) {
        return tabs.add(title, component);
    }
}