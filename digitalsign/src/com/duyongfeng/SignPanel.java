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
	//ѡ���ļ��İ�ť
	private JButton choseBtn;
	//�����ļ�·���������
    private JTextField fileField;
	//ǩ���İ�ť
	private JButton signBtn;
	//�Ƿ��ڸǵ������б�
	private JComboBox isCoverJcombox;
	//˽Կ
	private JTextArea prKeyArea;
	//��Կ
	private JTextArea puKeyArea; 
	//�ڸ���Ϣ
	private JTextArea coversArea; 
	//�����ڸ���İ�ť
	private JButton proCoverCode;
	//��������
	private JButton saveCover;
	//ѡ��ǩ�������ļ��ı���·��
	private JButton savePath;
	//��ʾ��ѡǩ�������ļ��ı���·��
	private JTextField pathField;
	private JLabel signState;
	public SignPanel(){
		choseBtn=new JButton("ѡ���ļ�");
		savePath=new JButton("ѡ��·��");
		proCoverCode=new JButton("��������");
		saveCover=new JButton("��������");
		fileField=new JTextField();
		pathField=new JTextField();
		signBtn=new JButton("����ǩ��");
		JLabel jlt1=new JLabel("ǩ���ļ�:");
		JLabel jlt2=new JLabel("����˽Կ:");
		JLabel jlt3=new JLabel("��Կģ��:");
		JLabel jlt4=new JLabel("ǩ��״̬:");
		JLabel jlt5=new JLabel("�Ƿ��ڸ�:");
		JLabel jlt6=new JLabel("�ڸ���Ϣ:");
		JLabel jlt7=new JLabel("���·��:");
		String[] data = {"��", "��"};
		isCoverJcombox=new JComboBox(data);
		signState=new JLabel("");
		signState.setFont(new Font("�����п�",5,18));
		signState.setForeground(Color.BLUE);
//		JLabel jlt3=new JLabel("����˽Կ��");
//		JLabel jlt4=new JLabel("����˽Կ��");
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
		signBtn=new JButton("����ǩ��");
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
		//ѡ���ļ�����
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
		//ѡ��ǩ���ļ�����·��
		}else if(e.getSource()==savePath){
			pathField.setEditable(true);
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.showOpenDialog(this);
			String path = chooser.getSelectedFile().getPath();
			pathField.setText(path);
			pathField.setEditable(false);
		//ǩ������
		}else if(e.getSource()==signBtn){
			//��ȡҪǩ�����ļ��ĵ�ַ
			String fileStr=fileField.getText().trim();
			String prStr=prKeyArea.getText().trim();
			String puStr=puKeyArea.getText().trim();
			String codeStr=coversArea.getText().trim();
			String pathStr=pathField.getText().trim();
			//�ļ���ַΪ�յĻ���������ʾ��Ϣ
			if(fileStr==null || "".equals(fileStr)){
				JOptionPane.showMessageDialog(this,"��ѡ��Ҫǩ�����ļ�");
				return;
			}
			if(pathStr==null || "".equals(pathStr)){
				JOptionPane.showMessageDialog(this,"��ѡ��ǩ������·��");
				return;
			}
			if(isCoverJcombox.getSelectedIndex()==0){
				//�ж���Կ�Ƿ�����
				if(codeStr==null || "".equals(codeStr)||prStr==null || "".equals(prStr)||puStr==null || "".equals(puStr)){
					JOptionPane.showMessageDialog(this,"��Կ���ڸ��벻��Ϊ�գ�\n��������Կ���ڸ���");
				}else{
					if(!SignUtils.isNumeric(puStr)||!SignUtils.isNumeric(prStr)||!SignUtils.isNumeric(codeStr)){
						JOptionPane.showMessageDialog(this,"��Կ���ڸ��벻�ܺ��������ַ�\n����ȷ���룡");
					    return;
					}
					File f=new File(fileStr);
					//ǩ���ļ����ڵĻ����ļ�����ǩ��
					if(f.exists()){
						//���ļ�������Ϣ�ڸǵ�ǩ��
						signFile(f,0);
						//�����ڵĻ�����
					}else{
						JOptionPane.showMessageDialog(this,"ǩ���ļ������ڣ�������ѡ��");
					}
				}
			}else{
				//�ж���Կ�Ƿ�����
				if(prStr==null || "".equals(prStr)||puStr==null || "".equals(puStr)){
					JOptionPane.showMessageDialog(this,"��Կ����Ϊ�գ�\n��������Կ");
				}else{
					if(!SignUtils.isNumeric(puStr)||!SignUtils.isNumeric(prStr)){
						JOptionPane.showMessageDialog(this,"��Կ�в��ܺ��������ַ�\n����ȷ���룡");
						return;
					}
					File f=new File(fileStr);
					//ǩ���ļ����ڵĻ����ļ�����ǩ��
					if(f.exists()){
						//���ļ���������Ϣ�ڸǵ�ǩ��
						signFile(f,1);
						//�����ڵĻ�����
					}else{
						JOptionPane.showMessageDialog(this,"ǩ���ļ������ڣ�������ѡ��");
					}
				}
			}
		}else if(e.getSource()==isCoverJcombox){
			//���ѡ����Ϣ�ڸ������ؿռ�����Խ�����ص�����
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
				 JOptionPane.showMessageDialog(this,"���ס����,�򱣴�����룡");
		}else if(e.getSource()==saveCover){
			JFileChooser chooser = new JFileChooser(".");
			TxtFileFilter txtFilter = new TxtFileFilter();
			//JavaFileFilter javaFilter = new JavaFileFilter();
			chooser.addChoosableFileFilter(txtFilter);
			//chooser.addChoosableFileFilter(javaFilter);
			// ����Ĭ�ϵ��ļ������������������,�������ӵ��ļ�������ΪĬ�Ϲ�����,������ΪjavaFilter
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
	//�������512λ���ڸ���
	private BigInteger proCover(){
		SecureRandom rand=new SecureRandom();
		return BigInteger.probablePrime(512,rand);
	}
	//��ԭ��Ϣǩ���ķ���
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
			//�����ǩ�����ļ������½�ǩ�����ļ�������ԭ�ļ���Ϣд���ļ�
			File signFile=new File(pathField.getText().trim()+"/"+f.getName());
			FileOutputStream signOut=new FileOutputStream(signFile);
			signOut.write(msgBytes);
			signOut.flush();
			//Ϊ���ڸ���Ϣ��ǩ��
			if(0==method){
				String codeStr=coversArea.getText().trim();
				msgBytes=preMd5Msg(msgBytes,codeStr);
			}
		    //�����ļ���ժҪ
			md5Engine.computeDigest(msgBytes);
			String msgDigest=md5Engine.msgDigest;
			//���ɶ�ժҪ��ǩ��
			byte[] signBytes=rsaEngine.encrypt(d, n,msgDigest.getBytes());
		    //���½���ǩ���ļ���ĩβ����ǩ������ժҪ
			signOut.write(signBytes);
			signOut.flush();
			signOut.close();
			signState.setText("ǩ���ɹ���");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	//��Դ��Ϣ���ֽ�����ǰ�����ڸ���
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
