package com.duyongfeng;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
public class SecretkeyPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//获取密钥的按钮
	private JButton proKeyBtn;
	//私钥
	private JTextArea prKeyArea;
	//公钥
	private JTextArea puKeyArea; 
	//保存密钥的按钮
	private JButton saveKeyBtn;
	//基于RSA的密钥生成器
	RsaEngine rsaEngine=new RsaEngine();
	public SecretkeyPanel(){
		setLayout(null);
		proKeyBtn=new JButton("获取密钥");
		saveKeyBtn=new JButton("保存密钥");
		prKeyArea=new JTextArea();
		puKeyArea=new JTextArea();
		prKeyArea.setLineWrap(true);
		puKeyArea.setLineWrap(true);
		JScrollPane srp1=new JScrollPane(prKeyArea);
		srp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane srp2=new JScrollPane(puKeyArea);
		srp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JLabel jlt1=new JLabel("私钥:");
		JLabel jlt2=new JLabel("公钥:");
		jlt1.setBounds(20,30,30,20);
		srp1.setBounds(50,30,300,120);
		jlt2.setBounds(20,160,30,20);
		srp2.setBounds(50,160,300,120);
		saveKeyBtn.setBounds(250,300,100,30);
		proKeyBtn.setBounds(50,300,100,30);
		add(proKeyBtn);
		add(jlt1);
		add(jlt2);
		add(srp1);
		add(srp2);
		add(saveKeyBtn);
		proKeyBtn.addActionListener(this);
		saveKeyBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==proKeyBtn){
			rsaEngine.produceKey(1024);
			prKeyArea.setText(rsaEngine.getD().toString());
			puKeyArea.setText(rsaEngine.getN().toString());
		}else if(e.getSource()==saveKeyBtn){
			JFileChooser chooser = new JFileChooser(".");
			TxtFileFilter txtFilter = new TxtFileFilter();
			//JavaFileFilter javaFilter = new JavaFileFilter();
			chooser.addChoosableFileFilter(txtFilter);
			//chooser.addChoosableFileFilter(javaFilter);
			// 设置默认的文件管理器。如果不设置,则最后添加的文件过滤器为默认过滤器,本例中为javaFilter
			chooser.setFileFilter(txtFilter);
			int rs = chooser.showSaveDialog(this); 
			if(rs==JFileChooser.APPROVE_OPTION){
				File file=chooser.getSelectedFile();
				File f=new File(file.getAbsolutePath()+".txt");
				//System.out.println(file.getAbsolutePath()+".txt");
				try {
					FileOutputStream out=new FileOutputStream(f);
					String prKey=prKeyArea.getText().trim();
					String puKey=puKeyArea.getText().trim();
					byte[] keyBytes=(prKey+"\n"+puKey).getBytes();
					out.write(keyBytes);
					out.flush();
					out.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
//文件过滤器
class TxtFileFilter extends FileFilter {

	public String getDescription() {
		return "*.txt(文本文件)";
	}
	public boolean accept(File file) {
		String name = file.getName();
		return name.toLowerCase().endsWith(".txt");
	}
}
