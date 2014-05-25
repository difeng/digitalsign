package com.duyongfeng;

public class Md5Engine {
	//将要生成的消息摘要
	String msgDigest="";
	//参与计算的常数数组
	int [] t=new int[64];
	//用于生成消息摘要的十六进制字符串
	String hex_chr = "0123456789abcdef";
	public Md5Engine(){
		initEngine();
	}
	//将32位整数按低字节在高位的形式转换为十六进制字符串
	private String changeTohex(int num) {
	        String str = "";
	        for (int j = 0; j <= 3; j++)
	            str = str + hex_chr.charAt((num >> (j * 8 + 4)) & 0x0F) +
	                  hex_chr.charAt((num >> (j * 8)) & 0x0F);
	        return str;
	}
	//初始化操作
	public void initEngine(){
		//初始化t数组
		double factor=4294967296.0;
		for(int s=0;s<64;s++){
			long r=(long) (factor*Math.abs(Math.sin(s+1)));
			r<<=32;
			r>>>=32;
			t[s]=(int)r;
		}
	}
    public void computeDigest(byte[] msgBytes){
    	//消息的字节数
		int len=msgBytes.length;
		//填充数组
		byte [] fillArr=null;
		//消息长度正好是512位的整数倍
		if((len%64)==0){
			fillArr=new byte[56+8];
		}else{
			//消息长度模64的余数
			int r=len%64;
			//消息长度模余小于56字节时填充使其长度达到56字节
			if(r<56){
				fillArr=new byte[56-r+8];
				//消息长度刚好满足要求模64余56
			}else if(r==56){
				fillArr=new byte[64+8];
			}else{
				fillArr=new byte[56+64-r+8];
			}
		}
		//填充消息数组
		if(fillArr!=null){
			for(int i=0;i<fillArr.length-8;i++){
				if(i==0){
					fillArr[i]=(byte) 0x80;
				}
			}
		}
		//添加消息长度
		long flen=msgBytes.length*8;
		for(int i=fillArr.length-8;i<fillArr.length;i++){
			fillArr[i]=(byte) (flen&0xff);
			flen>>>=8;
		}
		//填充后的最终消息字节数组
		byte[] afterFillMsg=new byte[fillArr.length+msgBytes.length];
		//将填充数组与原消息数组合并
		mergeTwoArr(fillArr,msgBytes,afterFillMsg);
	    //将填充后的最终消息字节数组用整形数组表示
		int [] arr=new int[afterFillMsg.length/4];
		for(int i=0;i<arr.length;i++){
			int t1=afterFillMsg[i*4]&0xff;
			int t2=afterFillMsg[i*4+1];
			int t3=afterFillMsg[i*4+2];
			int t4=afterFillMsg[i*4+3];
			t1|=t2<<8&0xff00;
			t1|=t3<<16&0xff0000;
			t1|=t4<<24&0xff000000;
			arr[i]=t1;
		}
		//初始化变量
		int a=0x67452301;
		int b=0xefcdab89;
		int c=0x98badcfe;
		int d=0x10325476;
		for(int i=0;i<arr.length/16;i++){
			int[] x=new int[16];
			//每循环一次，把原文存放在16个元素的数组x中
			for(int j=0;j<16;j++){
				x[j]=arr[i*16+j];
			}
			//将a保存为aa，b保存为bb，c保存为cc，d保存为dd
			int aa=a;
			int bb=b;
			int cc=c;
			int dd=d;
			//第一轮操作
			a=ff(a,b,c,d,x[0],7,t[0]);
			d=ff(d,a,b,c,x[1],12,t[1]);
			c=ff(c,d,a,b,x[2],17,t[2]);
			b=ff(b,c,d,a,x[3],22,t[3]);
			a=ff(a,b,c,d,x[4],7,t[4]);
			d=ff(d,a,b,c,x[5],12,t[5]);
			c=ff(c,d,a,b,x[6],17,t[6]);
			b=ff(b,c,d,a,x[7],22,t[7]);
			a=ff(a,b,c,d,x[8],7,t[8]);
			d=ff(d,a,b,c,x[9],12,t[9]);
			c=ff(c,d,a,b,x[10],17,t[10]);
			b=ff(b,c,d,a,x[11],22,t[11]);
			a=ff(a,b,c,d,x[12],7,t[12]);
			d=ff(d,a,b,c,x[13],12,t[13]);
			c=ff(c,d,a,b,x[14],17,t[14]);
			b=ff(b,c,d,a,x[15],22,t[15]);
			//第二轮操作
			a=gg(a,b,c,d,x[1],5,t[16]);
			d=gg(d,a,b,c,x[6],9,t[17]);
			c=gg(c,d,a,b,x[11],14,t[18]);
			b=gg(b,c,d,a,x[0],20,t[19]);
			a=gg(a,b,c,d,x[5],5,t[20]);
			d=gg(d,a,b,c,x[10],9,t[21]);
			c=gg(c,d,a,b,x[15],14,t[22]);
			b=gg(b,c,d,a,x[4],20,t[23]);
			a=gg(a,b,c,d,x[9],5,t[24]);
			d=gg(d,a,b,c,x[14],9,t[25]);
			c=gg(c,d,a,b,x[3],14,t[26]);
			b=gg(b,c,d,a,x[8],20,t[27]);
			a=gg(a,b,c,d,x[13],5,t[28]);
			d=gg(d,a,b,c,x[2],9,t[29]);
			c=gg(c,d,a,b,x[7],14,t[30]);
			b=gg(b,c,d,a,x[12],20,t[31]);
			//第三轮操作
			a=hh(a,b,c,d,x[5],4,t[32]);
			d=hh(d,a,b,c,x[8],11,t[33]);
			c=hh(c,d,a,b,x[11],16,t[34]);
			b=hh(b,c,d,a,x[14],23,t[35]);
			a=hh(a,b,c,d,x[1],4,t[36]);
			d=hh(d,a,b,c,x[4],11,t[37]);
			c=hh(c,d,a,b,x[7],16,t[38]);
			b=hh(b,c,d,a,x[10],23,t[39]);
			a=hh(a,b,c,d,x[13],4,t[40]);
			d=hh(d,a,b,c,x[0],11,t[41]);
			c=hh(c,d,a,b,x[3],16,t[42]);
			b=hh(b,c,d,a,x[6],23,t[43]);
			a=hh(a,b,c,d,x[9],4,t[44]);
			d=hh(d,a,b,c,x[12],11,t[45]);
			c=hh(c,d,a,b,x[15],16,t[46]);
			b=hh(b,c,d,a,x[2],23,t[47]);
			//第四轮操作
			a=ii(a,b,c,d,x[0],6,t[48]);
			d=ii(d,a,b,c,x[7],10,t[49]);
			c=ii(c,d,a,b,x[14],15,t[50]);
			b=ii(b,c,d,a,x[5],21,t[51]);
			a=ii(a,b,c,d,x[12],6,t[52]);
			d=ii(d,a,b,c,x[3],10,t[53]);
			c=ii(c,d,a,b,x[10],15,t[54]);
			b=ii(b,c,d,a,x[1],21,t[55]);
			a=ii(a,b,c,d,x[8],6,t[56]);
			d=ii(d,a,b,c,x[15],10,t[57]);
			c=ii(c,d,a,b,x[6],15,t[58]);
			b=ii(b,c,d,a,x[13],21,t[59]);
			a=ii(a,b,c,d,x[4],6,t[60]);
			d=ii(d,a,b,c,x[11],10,t[61]);
			c=ii(c,d,a,b,x[2],15,t[62]);
			b=ii(b,c,d,a,x[9],21,t[63]);
			//然后进行如下操作
			a=a+aa;
			b=b+bb;
			c=c+cc;
			d=d+dd;
		}
		msgDigest=changeTohex(a)+changeTohex(b)+changeTohex(c)+changeTohex(d);
    }
    public void mergeTwoArr(byte[] fillArr,byte[] msgBytes,byte[] afterFillMsg){
    	//将填充数组与原消息数组合并
		int k=0;
		while(k<afterFillMsg.length){
			if(k<msgBytes.length){
				afterFillMsg[k]=msgBytes[k];
			}else{
				afterFillMsg[k]=fillArr[k-msgBytes.length];
			}
			k++;
		}
    }
	//定义四个MD5基本的按位操作函数
	public int mf(int x,int y,int z){
		return (x&y)|((~x)&z);
	}
	public int mg(int x,int y,int z){
		return (x&z)|(y&(~z));
	}
	public int mh(int x,int y,int z){
		return x^y^z;
	}
	public int mi(int x,int y,int z){
		return y^(x|(~z));
	}
	//定义四个分别用于变换的函数
	public int ff(int a,int b,int c,int d,int mj,int s,int ti){
		return  (b+((a+mf(b,c,d)+mj+ti)<<s|(a+mf(b,c,d)+mj+ti)>>>(32-s)));
	}
	public int gg(int a,int b,int c,int d,int mj,int s,int ti){
		return  (b+((a+mg(b,c,d)+mj+ti)<<s|(a+mg(b,c,d)+mj+ti)>>>(32-s)));
	}
	public int hh(int a,int b,int c,int d,int mj,int s,int ti){
		return  (b+((a+mh(b,c,d)+mj+ti)<<s|(a+mh(b,c,d)+mj+ti)>>>(32-s)));
	}
	public int ii(int a,int b,int c,int d,int mj,int s,int ti){
		return  (b+((a+mi(b,c,d)+mj+ti)<<s|(a+mi(b,c,d)+mj+ti)>>>(32-s)));
	}
}
