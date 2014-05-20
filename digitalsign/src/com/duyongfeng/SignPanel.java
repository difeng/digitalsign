package com.duyongfeng;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;

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
public class SignPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//选择文件的按钮
	private JButton choseBtn;
	//输入文件路径的输入框
    private JTextField fileField;
	//签名的按钮
	private JButton signBtn;
	//是否掩盖的下拉列表
	private JComboBox isCoverJcombox;
	//私钥
	private JTextArea prKeyArea;
	//公钥
	private JTextArea puKeyArea; 
	//掩盖信息
	private JTextArea coversArea; 
	//生成掩盖码的按钮
	private JButton proCoverCode;
	//保存掩码
	private JButton saveCover;
	//选择签过名的文件的保存路径
	private JButton savePath;
	//显示所选签过名的文件的保存路径
	private JTextField pathField;
	private JLabel signState;
	public SignPanel(){
		choseBtn=new JButton("选择文件");
		savePath=new JButton("选择路径");
		proCoverCode=new JButton("生成掩码");
		saveCover=new JButton("保存掩码");
		fileField=new JTextField();
		pathField=new JTextField();
		signBtn=new JButton("数字签名");
		JLabel jlt1=new JLabel("签名文件:");
		JLabel jlt2=new JLabel("输入私钥:");
		JLabel jlt3=new JLabel("公钥模数:");
		JLabel jlt4=new JLabel("签名状态:");
		JLabel jlt5=new JLabel("是否掩盖:");
		JLabel jlt6=new JLabel("掩盖信息:");
		JLabel jlt7=new JLabel("输出路径:");
		String[] data = {"是", "否"};
		isCoverJcombox=new JComboBox(data);
		signState=new JLabel("");
		signState.setFont(new Font("华文行楷",5,18));
		signState.setForeground(Color.BLUE);
//		JLabel jlt3=new JLabel("输入私钥：");
//		JLabel jlt4=new JLabel("输入私钥：");
		prKeyArea=new JTextArea();
		prKeyArea.setLineWrap(true);
		JScrollPane srp1=new JScrollPane(prKeyArea);
		srp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		puKeyArea=new JTextArea();
		puKeyArea.setLineWrap(true);
		JScrollPane srp2=new JScrollPane(puKeyArea);
		srp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		coversArea=new JTextArea();
		coversArea.setLineWrap(true);
		JScrollPane srp3=new JScrollPane(coversArea);
		srp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		signBtn=new JButton("数字签名");
		setLayout(null);
		jlt1.setBounds(20,25,80,20);
		fileField.setBounds(100,25,230,20);
	    choseBtn.setBounds(350,25,100,20);
	   // jlt5.setBounds(20,80,80,20);
	    jlt2.setBounds(20,55,80,20);
	    srp1.setBounds(100,55,230,50);
	    jlt3.setBounds(20,115,80,20);
	    srp2.setBounds(100,115,230,50);
	    jlt5.setBounds(20,175,80,20);
	    isCoverJcombox.setBounds(100,175,50,20);
	    jlt6.setBounds(20,205,80,20);
	    srp3.setBounds(100,205,230,50);
	    proCoverCode.setBounds(350,175,100,20);
	    saveCover.setBounds(350,235,100,20);
	    jlt7.setBounds(20,270,80,20);
	    pathField.setBounds(100,270,230,20);
	    savePath.setBounds(350,270,100,20);
	    jlt4.setBounds(20,300,80,20);
	    signState.setBounds(100,300,150,20);
	    signBtn.setBounds(100,330,150,20);
	    add(jlt1);
	    add(jlt2);
	    add(jlt3);
	    add(jlt4);
	    add(jlt5);
	    add(jlt6);
	    add(srp3);
	    add(jlt7);
	    add(pathField);
	    add(savePath);
	    add(saveCover);
	    add(isCoverJcombox);
	    add(proCoverCode);
	    add(signState);
	    add(fileField);
	    add(choseBtn);
	    add(srp1);
	    add(srp2);
	    add(signBtn);
	    choseBtn.addActionListener(this);
	    signBtn.addActionListener(this);
	    proCoverCode.addActionListener(this);
	    isCoverJcombox.addActionListener(this);
	    saveCover.addActionListener(this);
	    savePath.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		//选择文件操作
		if(e.getSource()==choseBtn){
			fileField.setEditable(true);
			signState.setText("");
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		      "JPG,WPS,DOC,TXT,XML,XLSX,XLS", new String[] { "jpg" ,"wps","doc","xml","txt","xlsx","xls"});
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(this);
		    if (returnVal == 0){
		        File srcFile = chooser.getSelectedFile();
		       fileField.setText(srcFile.getAbsolutePath());
		
		    }
		    fileField.setEditable(false);
		//选择签署文件保存路径
		}else if(e.getSource()==savePath){
			pathField.setEditable(true);
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.showOpenDialog(this);
			String path = chooser.getSelectedFile().getPath();
			pathField.setText(path);
			pathField.setEditable(false);
		//签名操作
		}else if(e.getSource()==signBtn){
			//获取要签名的文件的地址
			String fileStr=fileField.getText().trim();
			String prStr=prKeyArea.getText().trim();
			String puStr=puKeyArea.getText().trim();
			String codeStr=coversArea.getText().trim();
			String pathStr=pathField.getText().trim();
			//文件地址为空的话，发出提示消息
			if(fileStr==null || "".equals(fileStr)){
				JOptionPane.showMessageDialog(this,"请选择要签名的文件");
				return;
			}
			if(pathStr==null || "".equals(pathStr)){
				JOptionPane.showMessageDialog(this,"请选择签名保存路径");
				return;
			}
			if(isCoverJcombox.getSelectedIndex()==0){
				//判断密钥是否输入
				if(codeStr==null || "".equals(codeStr)||prStr==null || "".equals(prStr)||puStr==null || "".equals(puStr)){
					JOptionPane.showMessageDialog(this,"密钥或掩盖码不能为空！\n请输入密钥和掩盖码");
				}else{
					if(!SignUtils.isNumeric(puStr)||!SignUtils.isNumeric(prStr)||!SignUtils.isNumeric(codeStr)){
						JOptionPane.showMessageDialog(this,"密钥或掩盖码不能含非数字字符\n请正确输入！");
					    return;
					}
					File f=new File(fileStr);
					//签名文件存在的话对文件进行签名
					if(f.exists()){
						//对文件进行信息掩盖的签名
						signFile(f,0);
						//不存在的话警告
					}else{
						JOptionPane.showMessageDialog(this,"签名文件不存在，请重新选择");
					}
				}
			}else{
				//判断密钥是否输入
				if(prStr==null || "".equals(prStr)||puStr==null || "".equals(puStr)){
					JOptionPane.showMessageDialog(this,"密钥不能为空！\n请输入密钥");
				}else{
					if(!SignUtils.isNumeric(puStr)||!SignUtils.isNumeric(prStr)){
						JOptionPane.showMessageDialog(this,"密钥中不能含非数字字符\n请正确输入！");
						return;
					}
					File f=new File(fileStr);
					//签名文件存在的话对文件进行签名
					if(f.exists()){
						//对文件进行无信息掩盖的签名
						signFile(f,1);
						//不存在的话警告
					}else{
						JOptionPane.showMessageDialog(this,"签名文件不存在，请重新选择");
					}
				}
			}
		}else if(e.getSource()==isCoverJcombox){
			//如果选择信息掩盖则对相关空间的属性进行相关的设置
			if(isCoverJcombox.getSelectedIndex()==1){
				coversArea.setEditable(false);
				coversArea.setBackground(Color.LIGHT_GRAY);
				proCoverCode.setEnabled(false);
			}else{
				coversArea.setEditable(true);
				coversArea.setBackground(Color.WHITE);
				proCoverCode.setEnabled(true);
			}
		}else if(e.getSource()==proCoverCode){
				 String coverStr=proCover().toString();
				 coversArea.setText(coverStr);
				 JOptionPane.showMessageDialog(this,"请记住掩码,或保存好掩码！");
		}else if(e.getSource()==saveCover){
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
					String coverCode=coversArea.getText().trim();
					byte[] keyBytes=coverCode.getBytes();
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
	//随机生成512位的掩盖码
	private BigInteger proCover(){
		SecureRandom rand=new SecureRandom();
		return BigInteger.probablePrime(512,rand);
	}
	//对原消息签名的方法
	public void signFile(File f,int method){
		Md5Engine md5Engine=new Md5Engine();
		RsaEngine rsaEngine=new RsaEngine();
		BigInteger d=new BigInteger(prKeyArea.getText().trim());
		BigInteger n=new BigInteger(puKeyArea.getText().trim());
		try {
			FileInputStream in=new FileInputStream(f);
			byte[] msgBytes=new byte[(int) f.length()];
			in.read(msgBytes);
			in.close();
			//在输出签名的文件夹中新建签名的文件，并将原文件信息写入文件
			File signFile=new File(pathField.getText().trim()+"/"+f.getName());
			FileOutputStream signOut=new FileOutputStream(signFile);
			signOut.write(msgBytes);
			signOut.flush();
			//为带掩盖信息的签名
			if(0==method){
				String codeStr=coversArea.getText().trim();
				msgBytes=preMd5Msg(msgBytes,codeStr);
			}
		    //生成文件的摘要
			md5Engine.computeDigest(msgBytes);
			String msgDigest=md5Engine.msgDigest;
			//生成对摘要的签名
			byte[] signBytes=rsaEngine.encrypt(d, n,msgDigest.getBytes());
		    //在新建的签名文件的末尾加上签过名的摘要
			signOut.write(signBytes);
			signOut.flush();
			signOut.close();
			signState.setText("签名成功！");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	//在源消息的字节数组前加上掩盖码
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
