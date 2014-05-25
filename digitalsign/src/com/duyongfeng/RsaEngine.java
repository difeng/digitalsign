package com.duyongfeng;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RsaEngine {
	private final static SecureRandom rand=new SecureRandom();
    //p与q为两个大素数
	private BigInteger p;
	private BigInteger q;
	//公钥模数
	private BigInteger n;
	//公钥
	private BigInteger e;
	//私钥
	private BigInteger d;
	public BigInteger getN() {
		return n;
	}
	public BigInteger getE() {
		return e;
	}
	public BigInteger getD() {
		return d;
	}
	public RsaEngine(){
	}
	//产生长度为N位的公钥和私钥
	public void produceKey(int N){
		//产生两个N/2位的大素数p和q
		p=BigInteger.probablePrime(N/2,rand);
		q=BigInteger.probablePrime(N/2,rand);
		//计算(p-1)*(q-1)
		BigInteger eulerFun=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		//计算模数p*q
		n=p.multiply(q);
		//选取合适的公钥
		e=new BigInteger("65537");
		//计算出私钥d，即e的模n的逆
		d=e.modInverse(eulerFun);
	}
	//加密操作
	public byte[] encrypt(BigInteger e,BigInteger n,byte [] msgBytes){
		return new BigInteger(msgBytes).modPow(e,n).toByteArray();
	}
	//解密操作
	public byte[] decrypt(BigInteger d,BigInteger n,byte [] Ciphertext){
		return new BigInteger(Ciphertext).modPow(d,n).toByteArray();
	}
}
