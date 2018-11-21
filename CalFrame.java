package Cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 界面类
 * @author Rain
 *
 */
public class CalFrame extends JFrame {
	// 显示计算的textField
	private TextField tf = null;

	// 数组mOp来保存操作符
	private String[] mOp = {"", "MC", "MR", "MS", "M+" };

	// 数组rOp保存对于结果操作的操作符
	private String[] rOp = { "BACK", "CE", "C" };

	// 数组nOp保存数字
	private String[] nOp = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*",
			"%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };

	//M操作标志按钮
	JButton button = null;
	
	// 监听器
	private ActionListener actionListener = null;

	// 业务逻辑
	private CalService calService = new CalService();

	// 用来设置面板的大小
	private final int PRE_HEIGHT = 250;
	private final int PRE_WIDTH = 360;
	
	
	public CalFrame() {
		super();
		initialize();
	}

	// 初始化界面
	private void initialize() {
		/**
		 * 设置该框架
		 */
		// 设置标题为计算机
		this.setTitle("计算器");
		// 设置框架的布局方式
//		 this.setLayout(new BorderLayout(10,1));

		/**
		 * 显示计算结果的面板
		 */
		// 创建显示计算结果的面板panel1
		JPanel panel = new JPanel();
		// 设置panel1此面板的布局方式
		panel.setLayout(new BorderLayout(10, 1));
		// 将TextFile添加到面板中,并设置其位置
		panel.add(getTextField(),BorderLayout.NORTH);
	
		// 设置此面板的首选大小
		panel.setPreferredSize(new Dimension(PRE_WIDTH, PRE_HEIGHT));
		
		//事件监听器初始化
		actionListener = this.getActionListener();
		

		/**
		 * 获取mOp的操作键 ,并将其添加到面板panel2上
		 */
		//
		JButton[] mButtons = getmButtons();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 1, 0, 5));
		for (JButton jButton : mButtons) {
			panel1.add(jButton);
		}

		
		//第二大块面板
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(1, 5));
		/**
		 * 获取rOp的操作键,并添加到panel21上
		 */
		JButton[] rButtons = getrButtons();
		JPanel panel21 = new JPanel();
		panel21.setLayout(new GridLayout(1, 3, 3, 3));
		for (JButton jButton : rButtons) {
			panel21.add(jButton);
		}

		/** 
		 * 获取nOp的操作键,并添加到面板panel22
		 */
		JButton[] nButtons = getnButtons();
		JPanel panel22 = new JPanel();
		panel22.setLayout(new GridLayout(4, 5, 3, 5));
		for (JButton jButton : nButtons) {
			panel22.add(jButton);
		}

		/** 
		 * 所有面板组合
		 */
		panel2.add(panel21, BorderLayout.NORTH);
		panel2.add(panel22, BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
	}

	// 获取nOp操作键
	private JButton[] getnButtons() {
		JButton[] nButtons = new JButton[nOp.length];
		String reds = "+-*/="; 
		for (int i = 0; i < nOp.length; i++) {
			nButtons[i] = new JButton(nOp[i]);
			
			if(reds.contains(nOp[i])){
				nButtons[i].setForeground(Color.red);
			}else{
				nButtons[i].setForeground(Color.blue);
			}
			
			//给按钮添加事件
			nButtons[i].addActionListener(actionListener);
		}

		return nButtons;
	}

	// 获取rOp的操作键
	private JButton[] getrButtons() {
		JButton[] rButtons = new JButton[rOp.length];

		for (int i = 0; i < rOp.length; i++) {
			rButtons[i] = new JButton(rOp[i]);
			//给按钮添加事件
			rButtons[i].addActionListener(actionListener);
			//按钮设置颜色
			rButtons[i].setForeground(Color.red);
		}

		return rButtons;
	}

	// 获取mOp的操作键
	private JButton[] getmButtons() {
		JButton[] mButtons = new JButton[mOp.length];

		for (int i = 0; i < mOp.length; i++) {
			mButtons[i] = new JButton(mOp[i]);
			//给按钮添加事件
			mButtons[i].addActionListener(actionListener);
			//按钮设置颜色
			mButtons[i].setForeground(Color.red);
		}

		return mButtons;
	}

	// 获取TextField
	private TextField getTextField() {
		if (tf == null) {
			tf = new TextField("0");
			tf.setEditable(false);
			tf.setBackground(Color.white);
		}
		return tf;
	}
	
	/*
	 * 事件处理
	 */
	private ActionListener getActionListener(){
		actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				String result = null;
				
				try{
					result = calService.callMethod(cmd,tf.getText());
				}catch(Exception e1){
					e1.printStackTrace();
				}
			
				/*
				 * 处理button的标记
				 */
				if(cmd.indexOf("MC") == 0){
					button.setText("");
				}else if(cmd.indexOf("M") == 0 && calService.getStore() > 0){
					button.setText("M");
				}
				
				//设置计算结果
				if(result != null){
					tf.setText(result);
				}
			
			}
		};
		
		return actionListener;
	}

}
