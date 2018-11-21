package Cal;
/**
 * �����߼�ҵ����
 * @author Rain
 *
 */
public class CalService {
	//��һ��������
	private String firstNum = null;
	//�ڶ���������
	private String secondNum = null;
	//�ϴβ���
//	private String lastOp = null;
	//��ʶ   �Ƿ�Ϊ�ڶ���������
	private boolean isSecondNum = false;
	
	//����
	private String numbers = "0123456789.";
	//�����
	private String operations = "+-*/";
	
	//���������
	private String operate = null;
	
	//������
	private double store = 0.0;
	
	
	
	/*
	 * ���÷��� ���ҷ��ؽ��
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
	 * ʵ�ִ洢��������
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
	 * ����%�ٷ���
	 */
	private String percent(String text){
		return String.valueOf(MyMath.multiply(0.01, Double.valueOf(text)));
	}
	
	
	/*
	 * ���㿪��
	 */
	private String sqrt(String text) {
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}

	
	/*
	 * ��������
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
	 * ����������
	 */
	private String setNegative(String text) {
		if(text.indexOf("-") == 0){
			return text.substring(1);
		}
		return text.equals("0") ? "0" : "-"+text;
	}

	
	/*
	 * ���ؼ�����
	 */
	private String calculate(String text) throws Exception {
		//��ʼ���ڶ���������
		double second = 0.0;
		if(secondNum == null){
			secondNum = text;
		}else{
			secondNum = secondNum;
		}
		
		second = Double.valueOf(secondNum);
		
		//������Ϊ��ʱ,����ֵΪ��.��������
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
		
		//�õڶ����������������ֵ�ǰ��ֵ
		if(secondNum == null){
			secondNum = text;
		}else{
			secondNum = secondNum;
		}
		
		this.isSecondNum = true;
		
		return firstNum; 
	}

	/*
	 * ���ò����ַ�,���ұ����һ������,��isSecondNum����Ϊtrue
	 */
	private String setOperate(String cmd, String text) {
		this.firstNum = text;
		this.operate = cmd;
		this.isSecondNum = true;
		return null;
	}

	
	/*
	 * ����ƴ��
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

	
	//��������
	private String backSpace(String text) {
		return text.equals("0") || text.equals("") ? "0" : text.substring(0, text.length()-1);
	}

	
	//���ı�������Ϊ0
	private String clear(String text) {
		return "0";
	}

	
	/*
	 * ������м�¼
	 * ����һ������������Ϊ0,�ڶ�������������Ϊnull
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
