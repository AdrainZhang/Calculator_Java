package Cal;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * 处理计算
 * @author Rain
 *
 */
public class MyMath {
	//保留默认小数后20位
	private static final int DEFAULT_SCALE = 20; 
	
	//加
	public static double add(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.add(second).doubleValue();
	}
	
	//减
	public static double subtract(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.subtract(second).doubleValue();
		
	}
	
	//乘
	public static double multiply(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.multiply(second).doubleValue();
	}
	
	/*
	 * 除
	 *	如果结果为无限小数,需要对结果进行取舍!
	 */
	public static double divide(double num1,double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		/*
		 * divide(被除数,保留几位小数,取舍模式);
		 * 
		 * BigDecimal.ROUND_HALF_UP   表示四舍五入模式
		 */
		return first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//获取BigDecimal
	private static BigDecimal getBigDecimal(double num){
		return new BigDecimal(num);
	}
	

}
