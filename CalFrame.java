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
 * ������
 * @author Rain
 *
 */
public class CalFrame extends JFrame {
	// ��ʾ�����textField
	private TextField tf = null;

	// ����mOp�����������
	private String[] mOp = {"", "MC", "MR", "MS", "M+" };

	// ����rOp������ڽ�������Ĳ�����
	private String[] rOp = { "BACK", "CE", "C" };

	// ����nOp��������
	private String[] nOp = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*",
			"%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };

	//M������־��ť
	JButton button = null;
	
	// ������
	private ActionListener actionListener = null;

	// ҵ���߼�
	private CalService calService = new CalService();

	// �����������Ĵ�С
	private final int PRE_HEIGHT = 250;
	private final int PRE_WIDTH = 360;
	
	
	public CalFrame() {
		super();
		initialize();
	}

	// ��ʼ������
	private void initialize() {
		/**
		 * ���øÿ��
		 */
		// ���ñ���Ϊ�����
		this.setTitle("������");
		// ���ÿ�ܵĲ��ַ�ʽ
//		 this.setLayout(new BorderLayout(10,1));

		/**
		 * ��ʾ�����������
		 */
		// ������ʾ�����������panel1
		JPanel panel = new JPanel();
		// ����panel1�����Ĳ��ַ�ʽ
		panel.setLayout(new BorderLayout(10, 1));
		// ��TextFile��ӵ������,��������λ��
		panel.add(getTextField(),BorderLayout.NORTH);
	
		// ���ô�������ѡ��С
		panel.setPreferredSize(new Dimension(PRE_WIDTH, PRE_HEIGHT));
		
		//�¼���������ʼ��
		actionListener = this.getActionListener();
		

		/**
		 * ��ȡmOp�Ĳ����� ,��������ӵ����panel2��
		 */
		//
		JButton[] mButtons = getmButtons();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 1, 0, 5));
		for (JButton jButton : mButtons) {
			panel1.add(jButton);
		}

		
		//�ڶ�������
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(1, 5));
		/**
		 * ��ȡrOp�Ĳ�����,����ӵ�panel21��
		 */
		JButton[] rButtons = getrButtons();
		JPanel panel21 = new JPanel();
		panel21.setLayout(new GridLayout(1, 3, 3, 3));
		for (JButton jButton : rButtons) {
			panel21.add(jButton);
		}

		/** 
		 * ��ȡnOp�Ĳ�����,����ӵ����panel22
		 */
		JButton[] nButtons = getnButtons();
		JPanel panel22 = new JPanel();
		panel22.setLayout(new GridLayout(4, 5, 3, 5));
		for (JButton jButton : nButtons) {
			panel22.add(jButton);
		}

		/** 
		 * ����������
		 */
		panel2.add(panel21, BorderLayout.NORTH);
		panel2.add(panel22, BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
	}

	// ��ȡnOp������
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
			
			//����ť����¼�
			nButtons[i].addActionListener(actionListener);
		}

		return nButtons;
	}

	// ��ȡrOp�Ĳ�����
	private JButton[] getrButtons() {
		JButton[] rButtons = new JButton[rOp.length];

		for (int i = 0; i < rOp.length; i++) {
			rButtons[i] = new JButton(rOp[i]);
			//����ť����¼�
			rButtons[i].addActionListener(actionListener);
			//��ť������ɫ
			rButtons[i].setForeground(Color.red);
		}

		return rButtons;
	}

	// ��ȡmOp�Ĳ�����
	private JButton[] getmButtons() {
		JButton[] mButtons = new JButton[mOp.length];

		for (int i = 0; i < mOp.length; i++) {
			mButtons[i] = new JButton(mOp[i]);
			//����ť����¼�
			mButtons[i].addActionListener(actionListener);
			//��ť������ɫ
			mButtons[i].setForeground(Color.red);
		}

		return mButtons;
	}

	// ��ȡTextField
	private TextField getTextField() {
		if (tf == null) {
			tf = new TextField("0");
			tf.setEditable(false);
			tf.setBackground(Color.white);
		}
		return tf;
	}
	
	/*
	 * �¼�����
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
				 * ����button�ı��
				 */
				if(cmd.indexOf("MC") == 0){
					button.setText("");
				}else if(cmd.indexOf("M") == 0 && calService.getStore() > 0){
					button.setText("M");
				}
				
				//���ü�����
				if(result != null){
					tf.setText(result);
				}
			
			}
		};
		
		return actionListener;
	}

}
