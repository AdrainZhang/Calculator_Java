package Cal;
/**
 * 计算逻辑业务类
 * @author Rain
 *
 */
public class CalService {
	//第一个操作数
	private String firstNum = null;
	//第二个操作数
	private String secondNum = null;
	//上次操作
//	private String lastOp = null;
	//标识   是否为第二个操作数
	private boolean isSecondNum = false;
	
	//数字
	private String numbers = "0123456789.";
	//运算符
	private String operations = "+-*/";
	
	//储存操作符
	private String operate = null;
	
	//储存器
	private double store = 0.0;
	
	
	
	/*
	 * 调用方法 并且返回结果
	 */
	public String callMethod(String cmd, String text) throws Exception {
		if(cmd.equals("C")){
			return clearAll();
		}else if(cmd.equals("CE")){
			return clear(text);
		}else if(cmd.equals("BACK")){
			return backSpace(text);
		}else if(numbers.indexOf(cmd) != -1){
			return catNum(cmd,text);
		}else if(operations.indexOf(cmd) != -1){
			return setOperate(cmd,text);
		}else if(cmd.equals("=")){
			return calculate(text);
		}else if(cmd.equals("+/-")){
			return setNegative(text);
		}else if(cmd.equals("1/x")){
			return setReciprocal(text);
		}else if(cmd.equals("sqrt")){
			return sqrt(text);
		}else if(cmd.equals("%")){
			return percent(text);
		}else{
			return mCmd(cmd,text);
		}
	}
	
	/*
	 * 实现存储操作命令
	 */
	private String mCmd(String cmd, String text) {
		if(cmd.equals("M+")){
			store = MyMath.add(store, Double.valueOf(text));
		}else if(cmd.equals("MC")){
			store = 0.0;
		}else if(cmd.equals("MR")){
			isSecondNum = true;
			return String.valueOf(store);
		}else if(cmd.equals("MS")){
			store = Double.valueOf(text).doubleValue();
		}
		return null;
	}
	
	/*
	 * 计算%百分数
	 */
	private String percent(String text){
		return String.valueOf(MyMath.multiply(0.01, Double.valueOf(text)));
	}
	
	
	/*
	 * 计算开方
	 */
	private String sqrt(String text) {
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}

	
	/*
	 * 倒数计算
	 */
	private String setReciprocal(String text) {
		if(text.equals("0")){
			return text;
		}else{
			this.isSecondNum = true; 
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}

	/*
	 * 设置正负数
	 */
	private String setNegative(String text) {
		if(text.indexOf("-") == 0){
			return text.substring(1);
		}
		return text.equals("0") ? "0" : "-"+text;
	}

	
	/*
	 * 返回计算结果
	 */
	private String calculate(String text) throws Exception {
		//初始化第二个操作数
		double second = 0.0;
		if(secondNum == null){
			secondNum = text;
		}else{
			secondNum = secondNum;
		}
		
		second = Double.valueOf(secondNum);
		
		//当除数为零时,返回值为零.不作处理
		if(second == 0.0 && operate.equals("/")){
			return "0";
		}
		
		
		if(this.operate.equals("+")){
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum), Double.valueOf(second)));
		}else if(this.operate.equals("-")){
			firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum), Double.valueOf(second)));
		}else if(this.operate.equals("*")){
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum), Double.valueOf(second)));
		}else if(this.operate.equals("/")){
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum), Double.valueOf(second)));
		}
		
		//让第二个操作数继续保持当前的值
		if(secondNum == null){
			secondNum = text;
		}else{
			secondNum = secondNum;
		}
		
		this.isSecondNum = true;
		
		return firstNum; 
	}

	/*
	 * 设置操作字符,并且保存第一个数字,将isSecondNum设置为true
	 */
	private String setOperate(String cmd, String text) {
		this.firstNum = text;
		this.operate = cmd;
		this.isSecondNum = true;
		return null;
	}

	
	/*
	 * 数字拼接
	 */
	private String catNum(String cmd, String text) {
		String ret = cmd;
		if(!text.equals("0")){
			if(isSecondNum){
				isSecondNum = false;
			}else{
				ret = text + cmd;
			}
		}
		if(ret.indexOf(".") == 0){
			ret = "0" + ret;
		}
		return ret;
	}

	
	//消减数字
	private String backSpace(String text) {
		return text.equals("0") || text.equals("") ? "0" : text.substring(0, text.length()-1);
	}

	
	//将文本框设置为0
	private String clear(String text) {
		return "0";
	}

	
	/*
	 * 清除所有记录
	 * 将第一个操作数设置为0,第二个操作数设置为null
	 */
	private String clearAll() {
		firstNum = "0";
		secondNum = null;	
		return firstNum;
	}


	/*
	 * 
	 */
	public double getStore() {
		return store;
	}
}
