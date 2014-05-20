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
	//�����ļ�·���������
	private JTextField fileField;
	//��֤ǩ���İ�ť
	private JButton verifyBtn;
	//��Կ
	private JTextArea puKeyArea; 
	//�ڸ���Ϣ
	private JTextArea coversArea; 
	//�Ƿ��ڸǵ������б�
	private JComboBox isCoverJcombox;
	private JLabel verifyState;
	public VerifyPanel(){
		choseBtn=new JButton("ѡ���ļ�");
		fileField=new JTextField();
		verifyBtn=new JButton("��֤ǩ��");
		JLabel jlt1=new JLabel("��֤�ļ�:");
		puKeyArea=new JTextArea();
		puKeyArea.setLineWrap(true);
		JScrollPane srp2=new JScrollPane(puKeyArea);
		srp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		coversArea=new JTextArea();
		coversArea.setLineWrap(true);
		JScrollPane srp3=new JScrollPane(coversArea);
		srp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		String[] data = {"��", "��"};
		isCoverJcombox=new JComboBox(data);
		JLabel jlt3=new JLabel("�Է���Կ:");
		JLabel jlt4=new JLabel("��֤״̬:");
		JLabel jlt5=new JLabel("�Ƿ��ڸ�:");
		JLabel jlt6=new JLabel("�ڸ���Ϣ:");
		verifyState=new JLabel("");
		verifyState.setFont(new Font("�����п�",5,18));
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
			//��ȡҪǩ�����ļ��ĵ�ַ
			String fileStr=fileField.getText().trim();
			if(fileStr==null || "".equals(fileStr)){
				JOptionPane.showMessageDialog(this,"��ѡ���ļ�");
				return;
			}
			String puKeyStr=puKeyArea.getText().trim();
			String codeStr=coversArea.getText().trim();
			if(isCoverJcombox.getSelectedIndex()==0){
				if(puKeyStr==null||"".equals(puKeyStr)||codeStr==null||"".equals(codeStr)){
					JOptionPane.showMessageDialog(this,"��Կ�����벻��Ϊ�գ�\n�����빫Կ�����룡");
					return;
				}else{
					if(!SignUtils.isNumeric(puKeyStr)||!SignUtils.isNumeric(codeStr)){
						JOptionPane.showMessageDialog(this,"��Կ���ڸ����в��ܺ��������ַ�\n����ȷ���룡");
						return;
					}
					File f=new File(fileStr);
					//Ҫ��֤��ǩ���ļ����ڵĻ����ļ�����ǩ��
					if(f.exists()){
						verifySign(f,0);
						//�����ڵĻ�����
					}else{
						JOptionPane.showMessageDialog(this,"Ҫ��֤���ļ������ڣ�������ѡ��");
					}  	
				}
			}else{
				//�ж���Կ�Ƿ�����
				if(puKeyStr==null||"".equals(puKeyStr)){
			    	JOptionPane.showMessageDialog(this,"��Կ����Ϊ�գ�\n�����빫Կ��");
					return;
			    }else{
			    	if(!SignUtils.isNumeric(puKeyStr)){
						JOptionPane.showMessageDialog(this,"��Կ�в��ܺ��������ַ�\n����ȷ���룡");
						return;
					}
			    	File f=new File(fileStr);
			    	//Ҫ��֤��ǩ���ļ����ڵĻ����ļ�����ǩ��
			    	if(f.exists()){
			    		verifySign(f,1);
			    		//�����ڵĻ�����
			    	}else{
			    		JOptionPane.showMessageDialog(this,"Ҫ��֤���ļ������ڣ�������ѡ��");
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
			//��ȡǩ�������ļ����ֽ�����
			byte[] msgBytes=new byte[(int) f.length()];
			in.read(msgBytes);
			in.close();
			//��ǩ�����ļ��з������ǩ��ǰ��Դ�ļ�
			byte[] srcBytes=new byte[msgBytes.length-128];
			for(int i=0;i<srcBytes.length;i++){
				srcBytes[i]=msgBytes[i];
			}
			//����Ϣ�ڸǵ�ǩ��
			if(0==method){
				String codeStr=coversArea.getText().trim();
				srcBytes=preMd5Msg(srcBytes,codeStr);
			}
			//����Դ�ļ���ժҪ
			md5Engine.computeDigest(srcBytes);
			String msgDigest=md5Engine.msgDigest;
			//��ǩ�����ļ��з������ǩ�������ļ�ժҪ
			byte[] summeryBytes=new byte[128];
			for(int i=msgBytes.length-128,j=0;i<msgBytes.length;i++){
				summeryBytes[j++]=msgBytes[i];
			}
			//��ǩ�������ļ�ժҪ������֤
			byte[] signBytes=rsaEngine.decrypt(pe,n,summeryBytes);
			if(msgDigest.equals(new String(signBytes))){
				verifyState.setText("ǩ����Ч!");
			}else{
				verifyState.setText("ǩ����Ч!");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    //������Ϣ��������Ϣ�ĺϲ�����ֽ�����
    public byte[] preMd5Msg(byte [] msgBytes,String codeStr){
    	byte [] codeBytes=codeStr.getBytes();
    	byte [] newMsgBytes=new byte[codeBytes.length+msgBytes.length];
    	//�����������ԭ��Ϣ����ϲ�
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
