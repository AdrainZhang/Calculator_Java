package Cal;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		CalFrame calFrame = new CalFrame();
		//调整窗口的大小,来适应组件的大小
		calFrame.pack();
		//设置可见
		calFrame.setVisible(true);
		//设置默认关闭操作
		calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置初始位置
		calFrame.setLocation(400,200);
	}
}
