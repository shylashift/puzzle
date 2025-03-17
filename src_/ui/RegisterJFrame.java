package ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class RegisterJFrame extends JFrame {
	
	
    public RegisterJFrame() {
    	//this调用对象的地址值
    	this.setSize(488, 500);
    	//设置界面的标题
    	this.setTitle("拼图游戏 注册");
    	//设置页面制顶
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
