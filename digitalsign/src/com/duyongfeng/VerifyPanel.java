package com.duyongfeng;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VerifyPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton choseBtn;
	//输入文件路径的输入框
	private JTextField fileField;
	//验证签名的按钮
	private JButton verifyBtn;
	//公钥
	private JTextArea puKeyArea; 
	//掩盖信息
	private JTextArea coversArea; 
	//是否掩盖的下拉列表
	private JComboBox isCoverJcombox;
	private JLabel verifyState;
	public VerifyPanel(){
		choseBtn=new JButton("选择文件");
		fileField=new JTextField();
		verifyBtn=new JButton("验证签名");
		JLabel jlt1=new JLabel("验证文件:");
		puKeyArea=new JTextArea();
		puKeyArea.setLineWrap(true);
		JScrollPane srp2=new JScrollPane(puKeyArea);
		srp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		coversArea=new JTextArea();
		coversArea.setLineWrap(true);
		JScrollPane srp3=new JScrollPane(coversArea);
		srp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		String[] data = {"是", "否"};
		isCoverJcombox=new JComboBox(data);
		JLabel jlt3=new JLabel("对方公钥:");
		JLabel jlt4=new JLabel("验证状态:");
		JLabel jlt5=new JLabel("是否掩盖:");
		JLabel jlt6=new JLabel("掩盖信息:");
		verifyState=new JLabel("");
		verifyState.setFont(new Font("华文行楷",5,18));
		verifyState.setForeground(Color.BLUE);
		setLayout(null);
		jlt1.setBounds(20,50,80,20);
		fileField.setBounds(100,50,230,20);
		choseBtn.setBounds(350,50,100,20);
		jlt3.setBounds(20,80,80,20);
		srp2.setBounds(100,80,230,70);
		jlt5.setBounds(20,160,80,20);
		isCoverJcombox.setBounds(100,160,50,20);
		jlt6.setBounds(20,190,80,20);
		srp3.setBounds(100,190,230,70);
		jlt4.setBounds(20,270,80,20);
		verifyState.setBounds(100,270,150,20);
		verifyBtn.setBounds(100,300,150,20);
		add(jlt1);
		add(jlt3);
		add(jlt4);
		add(jlt5);
		add(jlt6);
		add(srp3);
		add(isCoverJcombox);
		add(verifyState);
		add(srp2);
		add(verifyBtn);
		add(fileField);
		add(choseBtn);
		choseBtn.addActionListener(this);
		verifyBtn.addActionListener(this);
		isCoverJcombox.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==choseBtn){
			fileField.setEnabled(true);
			verifyState.setText("");
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					 "JPG,WPS,DOC,TXT,XML,XLSX,XLS", new String[] { "jpg" ,"wps","doc","xml","txt","xlsx","xls"});
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == 0) {
				File srcFile = chooser.getSelectedFile();
				fileField.setText(srcFile.getAbsolutePath());
			}
			fileField.setEnabled(false);
		}else if(e.getSource()==verifyBtn){
			//获取要签名的文件的地址
			String fileStr=fileField.getText().trim();
			if(fileStr==null || "".equals(fileStr)){
				JOptionPane.showMessageDialog(this,"请选择文件");
				return;
			}
			String puKeyStr=puKeyArea.getText().trim();
			String codeStr=coversArea.getText().trim();
			if(isCoverJcombox.getSelectedIndex()==0){
				if(puKeyStr==null||"".equals(puKeyStr)||codeStr==null||"".equals(codeStr)){
					JOptionPane.showMessageDialog(this,"公钥和掩码不能为空！\n请输入公钥和密码！");
					return;
				}else{
					if(!SignUtils.isNumeric(puKeyStr)||!SignUtils.isNumeric(codeStr)){
						JOptionPane.showMessageDialog(this,"密钥和掩盖码中不能含非数字字符\n请正确输入！");
						return;
					}
					File f=new File(fileStr);
					//要验证的签名文件存在的话对文件进行签名
					if(f.exists()){
						verifySign(f,0);
						//不存在的话警告
					}else{
						JOptionPane.showMessageDialog(this,"要验证的文件不存在，请重新选择");
					}  	
				}
			}else{
				//判断密钥是否输入
				if(puKeyStr==null||"".equals(puKeyStr)){
			    	JOptionPane.showMessageDialog(this,"公钥不能为空！\n请输入公钥！");
					return;
			    }else{
			    	if(!SignUtils.isNumeric(puKeyStr)){
						JOptionPane.showMessageDialog(this,"密钥中不能含非数字字符\n请正确输入！");
						return;
					}
			    	File f=new File(fileStr);
			    	//要验证的签名文件存在的话对文件进行签名
			    	if(f.exists()){
			    		verifySign(f,1);
			    		//不存在的话警告
			    	}else{
			    		JOptionPane.showMessageDialog(this,"要验证的文件不存在，请重新选择");
			    	}
			    }
			}
		}else if(e.getSource()==isCoverJcombox){
			if(isCoverJcombox.getSelectedIndex()==1){
				coversArea.setEditable(false);
				coversArea.setBackground(Color.LIGHT_GRAY);
			}else{
				coversArea.setEditable(true);
				coversArea.setBackground(Color.WHITE);
			}
		}
	}
    private void verifySign(File f,int method){
    	Md5Engine md5Engine=new Md5Engine();
		RsaEngine rsaEngine=new RsaEngine();
		BigInteger n=new BigInteger(puKeyArea.getText().trim());
		BigInteger pe=new BigInteger("65537");
		try {
			FileInputStream in=new FileInputStream(f);
			//FileOutputStream out=new FileOutputStream(f,true);
			//获取签过名的文件的字节数组
			byte[] msgBytes=new byte[(int) f.length()];
			in.read(msgBytes);
			in.close();
			//从签名的文件中分离出来签名前的源文件
			byte[] srcBytes=new byte[msgBytes.length-128];
			for(int i=0;i<srcBytes.length;i++){
				srcBytes[i]=msgBytes[i];
			}
			//带信息掩盖的签名
			if(0==method){
				String codeStr=coversArea.getText().trim();
				srcBytes=preMd5Msg(srcBytes,codeStr);
			}
			//生成源文件的摘要
			md5Engine.computeDigest(srcBytes);
			String msgDigest=md5Engine.msgDigest;
			//从签名的文件中分离出来签过名的文件摘要
			byte[] summeryBytes=new byte[128];
			for(int i=msgBytes.length-128,j=0;i<msgBytes.length;i++){
				summeryBytes[j++]=msgBytes[i];
			}
			//对签过名的文件摘要进行验证
			byte[] signBytes=rsaEngine.decrypt(pe,n,summeryBytes);
			if(msgDigest.equals(new String(signBytes))){
				verifyState.setText("签名有效!");
			}else{
				verifyState.setText("签名无效!");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    //计算消息和隐藏信息的合并后的字节数组
    public byte[] preMd5Msg(byte [] msgBytes,String codeStr){
    	byte [] codeBytes=codeStr.getBytes();
    	byte [] newMsgBytes=new byte[codeBytes.length+msgBytes.length];
    	//将填充数组与原消息数组合并
		int i=0;
		while(i<newMsgBytes.length){
			if(i<codeBytes.length){
				newMsgBytes[i]=codeBytes[i];
			}else{
				newMsgBytes[i]=msgBytes[i-codeBytes.length];
			}
			i++;
		}
    	return newMsgBytes;
    }
}
