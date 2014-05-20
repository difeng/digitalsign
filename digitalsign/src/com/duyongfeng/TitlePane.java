package com.duyongfeng;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TitlePane extends JPanel{
	private static final long serialVersionUID = 1L;
	ImageIcon  bk=new ImageIcon("image/title.jpg");
	Image img=bk.getImage();
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,500,100,this);  
	}	  
}

