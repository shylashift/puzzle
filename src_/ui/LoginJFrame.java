package ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class LoginJFrame extends JFrame {

	//表示登入页面,和登入相关的逻辑都写在这
	public LoginJFrame() {
		
		//this调用对象的地址值
		this.setSize(488, 430);
		//设置界面的标题
		this.setTitle("拼图游戏 登入");
		//设置页面置顶
		this.setAlwaysOnTop(true);
		//设置页面居中
		this.setLocationRelativeTo(null);
		//设置页面关闭
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//与this.setDefaultCloseOperation(3)效果一样
		//让页面显示出来,建议写在最后
		this.setVisible(true);
	}
	
}
