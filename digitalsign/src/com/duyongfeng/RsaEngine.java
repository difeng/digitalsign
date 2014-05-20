package com.duyongfeng;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RsaEngine {
	private final static SecureRandom rand=new SecureRandom();
    //p��qΪ����������
	private BigInteger p;
	private BigInteger q;
	//��Կģ��
	private BigInteger n;
	//��Կ
	private BigInteger e;
	//˽Կ
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
	//��������ΪNλ�Ĺ�Կ��˽Կ
	public void produceKey(int N){
		//��������N/2λ�Ĵ�����p��q
		p=BigInteger.probablePrime(N/2,rand);
		q=BigInteger.probablePrime(N/2,rand);
		//����(p-1)*(q-1)
		BigInteger eulerFun=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		//����ģ��p*q
		n=p.multiply(q);
		//ѡȡ���ʵĹ�Կ
		e=new BigInteger("65537");
		//�����˽Կd����e��ģn����
		d=e.modInverse(eulerFun);
	}
	//���ܲ���
	public byte[] encrypt(BigInteger e,BigInteger n,byte [] msgBytes){
		return new BigInteger(msgBytes).modPow(e,n).toByteArray();
	}
	//���ܲ���
	public byte[] decrypt(BigInteger d,BigInteger n,byte [] Ciphertext){
		return new BigInteger(Ciphertext).modPow(d,n).toByteArray();
	}
}
