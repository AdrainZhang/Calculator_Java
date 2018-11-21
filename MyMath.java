package Cal;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * �������
 * @author Rain
 *
 */
public class MyMath {
	//����Ĭ��С����20λ
	private static final int DEFAULT_SCALE = 20; 
	
	//��
	public static double add(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.add(second).doubleValue();
	}
	
	//��
	public static double subtract(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.subtract(second).doubleValue();
		
	}
	
	//��
	public static double multiply(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.multiply(second).doubleValue();
	}
	
	/*
	 * ��
	 *	������Ϊ����С��,��Ҫ�Խ������ȡ��!
	 */
	public static double divide(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		/*
		 * divide(������,������λС��,ȡ��ģʽ);
		 * 
		 * BigDecimal.ROUND_HALF_UP   ��ʾ��������ģʽ
		 */
		return first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//��ȡBigDecimal
	private static BigDecimal getBigDecimal(double num){
		return new BigDecimal(num);
	}
	

}
