package Cal;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		CalFrame calFrame = new CalFrame();
		//�������ڵĴ�С,����Ӧ����Ĵ�С
		calFrame.pack();
		//���ÿɼ�
		calFrame.setVisible(true);
		//����Ĭ�Ϲرղ���
		calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ó�ʼλ��
		calFrame.setLocation(400,200);
	}
}
