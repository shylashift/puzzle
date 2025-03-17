package ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
 


public class GameJFrame extends JFrame implements KeyListener,ActionListener {
	//这是游戏主界面,关于和游戏相关的逻辑都写在这
	Random r=new Random();
	String path="..\\project\\image\\animal\\animal";
	int[][] data = new int[4][4];
	int n,m;
	int[][] truedata= {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
	int step;
	int sum=r.nextInt(8)+1;
	
	//创建选项的下拉菜单
	JMenuItem replyItem=new JMenuItem("重新开始游戏");
	JMenuItem reloginItem=new JMenuItem("重新登入");
	JMenuItem closeItem=new JMenuItem("关闭游戏");
	JMenuItem clubItem=new JMenuItem("公众号");
	JMenuItem beautyItem=new JMenuItem("美女");
	JMenuItem animalItem=new JMenuItem("动物");
	JMenuItem sportItem=new JMenuItem("运动");
	
	public GameJFrame() {
		//初始化界面
		initJFrame();
		//初始化菜单
		initJMenuBar();
		//初始化数据(打乱图片)
		initData();
		//初始化图片(根据打乱之后的顺序加载图片)
		initPicture();
		//让页面显示出来,建议写在最后
		this.setVisible(true);
	}
	
	private void initData() {
		// 将图片打乱顺序
		// 先定义一个一维数组
		int[] temp = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		// 打乱数组的顺序
		int leng = temp.length;
		Random r = new Random();
		for (int i = 0; i < leng; i++) 
		{
			int index = r.nextInt(leng);
			int number = temp[i];
			temp[i] = temp[index];
			temp[index] = number;

		}
		// 添加到二维数组中
		
		for (int i = 0; i < leng; i++) 
		{
			if(temp[i]==0)
			{
				n=i/4;
				m=i%4;
			}
			data[i / 4][i % 4] = temp[i];
		}
	}

	private void initPicture() {
		
		//清空隐藏容器的所有内容
		this.getContentPane().removeAll();
		
		//先添加的图片在上层,后添加的图片在下层
		if(victory())
		{
			JLabel win=new JLabel(new ImageIcon("..\\project\\image\\win.png"));
			win.setBounds(203, 283,197, 73);
			this.getContentPane().add(win);
		}
		JLabel stepcount=new JLabel("步数:"+step);
		stepcount.setBounds(50, 30, 100, 20);
		this.getContentPane().add(stepcount);
		
		//给图片指定位置
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				int count=data[i][j];
				//创建一个JLabel的对象(管理容器)
				//绝对路径:从盘符开始,如C:
				//相对路径:从当前同一个文件夹开始
				JLabel jLabel=new JLabel(new ImageIcon(path+sum+"\\"+count+".jpg"));
				//定义位置(x,y)
				jLabel.setBounds(j*105+83,i*105+134,105,105);
				//使用边框border
				jLabel.setBorder(new BevelBorder(1));
				//通过getContentPane()获得隐藏容器,然后再将图片添加到隐藏容器内
				this.getContentPane().add(jLabel);
				
			}
		}
		
		JLabel background=new JLabel(new ImageIcon("..\\project\\image\\background.png"));
		background.setBounds(40, 40, 508, 560);
		this.getContentPane().add(background);
		
		//刷新图片
		this.getContentPane().repaint();
	}			

	private void initJFrame() {
		//this调用对象的地址值
		this.setSize(603, 680);
		//设置界面的标题
		this.setTitle("拼图游戏单机版");
		//设置页面置顶
		this.setAlwaysOnTop(true);
		//设置页面居中
		this.setLocationRelativeTo(null);
		//设置页面关闭
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//与this.setDefaultCloseOperation(3)效果一样
		
		//取消默认的居中设置,只有取消了才会按照x,y轴的形式添加组件
		this.setLayout(null);
		//给整个界面添加键盘监听处理事件
		this.addKeyListener(this);
	}
	
	private void initJMenuBar() {
		//初始化菜单
		//创建整个的菜单对象
		JMenuBar jMenuBar = new JMenuBar();
				
		//创建菜单上面的两个选项的对象
		JMenu function=new JMenu("功能");
		JMenu about = new JMenu("关于我们");
				
		JMenu pictureItem=new JMenu("更换图片");
				
		//将下拉菜单添加到选项中
		function.add(pictureItem);
		function.add(replyItem);
		function.add(reloginItem);
		function.add(closeItem);
		about.add(clubItem);
		pictureItem.add(beautyItem);
		pictureItem.add(sportItem);
		pictureItem.add(animalItem);
		
		
		//绑定事件
		replyItem.addActionListener(this);
		reloginItem.addActionListener(this);
		closeItem.addActionListener(this);
		clubItem.addActionListener(this);
		beautyItem.addActionListener(this);
		animalItem.addActionListener(this);
		sportItem.addActionListener(this);
				
		//将选项添加到菜单中
		jMenuBar.add(function);
		jMenuBar.add(about);
				
		//给整个界面设置菜单
		this.setJMenuBar(jMenuBar);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/*//按下不松时则可调用此方法
		//shift键的值为16
		int num=e.getKeyCode();
		System.out.println(num);
		if(num==16)
		{
			//先把界面所有的图片删除
			this.getContentPane().removeAll();
			//加载图片
			JLabel all=new JLabel(new ImageIcon("..\\project\\image\\animal\\animal3\\all.jpg"));
			all.setBounds(83, 140,420 , 420);
			this.getContentPane().add(all);
			//加载背景图片
			JLabel background=new JLabel(new ImageIcon("..\\project\\image\\background.png"));
			background.setBounds(40, 40, 508, 560);
			this.getContentPane().add(background);
			//刷新
			this.getContentPane().repaint();
		}*/
		//使用不了,每个键盘录进去的都是0
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(victory())
		{
			return;
		}
		
		//上键值为:38,下键:40,左键:37,右键:39
			int count=e.getKeyCode();
			System.out.println(count);
			if(count==37)
			{
				System.out.println("向左移动");
				if(m==3)
				{
					return;
				}
				data[n][m]=data[n][m+1];
				data[n][m+1]=0;
				m++;
				step++;
				//调用方法,按照最新的数字加载图片
				initPicture();
			}
			else if(count==38)
			{
				System.out.println("向上移动");
				if(n==3)
				{
					return;
				}
				data[n][m]=data[n+1][m];
				data[n+1][m]=0;
				n++;
				step++;
				initPicture();	
			}
			else if(count==39)
			{
				if(m==0)
				{
					return;
				}
				System.out.println("向右移动");
				data[n][m]=data[n][m-1];
				data[n][m-1]=0;
				m--;
				step++;
				initPicture();
			}
			else if(count==40)
			{
				if(n==0)
				{
					return;
				}
				System.out.println("向下移动");
				data[n][m]=data[n-1][m];
				data[n-1][m]=0;
				n--;
				step++;
				initPicture();
			}
			else if(count==83)
			{
				image();
			}
			else if(count==65)
			{
				initPicture();
			}
			else if(count==87)
			{
				 data=new int[][]{
					{1,2,3,4},
					{5,6,7,8},
					{9,10,11,12},
					{13,14,15,0}
				};
				initPicture();
			}
		}

	private boolean victory() {
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data.length;j++)
			{
				//如果有一个不相等,直接跳出循环
				if(data[i][j]!=truedata[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

	private void image() {
		// 先把界面所有的图片删除
		this.getContentPane().removeAll();
		// 加载图片
		JLabel all = new JLabel(new ImageIcon(path+sum+"\\all.jpg"));
		all.setBounds(83, 140, 420, 420);
		this.getContentPane().add(all);
		// 加载背景图片
		JLabel background = new JLabel(new ImageIcon("..\\project\\image\\background.png"));
		background.setBounds(40, 40, 508, 560);
		this.getContentPane().add(background);
		// 刷新
		this.getContentPane().repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==replyItem)
		{
			//步数清零
			step=0;
			//先打乱数据
			initData();
			//在加载图片
			initPicture();
		}
		else if(obj==reloginItem)
		{
			//关闭游戏页面
			this.setVisible(false);
			//打开登入界面
			new LoginJFrame();
		}
		else if(obj==closeItem)
		{
			//直接关闭虚拟机
			System.exit(0);
		}
		else if(obj==clubItem)
		{
			//创建弹窗
			JDialog jdialog=new JDialog();
			//创建管理图片的容器
			JLabel jdJlabel=new JLabel(new ImageIcon("..\\project\\image\\this.png"));
			jdJlabel.setBounds(0, 0, 480, 450);
			jdialog.getContentPane().add(jdJlabel);
			//给弹框设置大小
			jdialog.setSize(526, 550);
			//给弹框置顶
			jdialog.setAlwaysOnTop(true);
			//让弹框居中
			jdialog.setLocationRelativeTo(null);
			//弹框不关闭,无法对下面操作进行
			jdialog.setModal(true);
			//让弹框显示出来
			jdialog.setVisible(true);
		}
		else if(obj==beautyItem)
		{
			path="..\\project\\image\\girl\\girl";
			sum=r.nextInt(13)+1;
			//步数清零
			step=0;
			//先打乱数据
			initData();
			//在加载图片
			initPicture();
			
		}
		else if(obj==animalItem)
		{
			path="..\\project\\image\\animal\\animal";
			sum=r.nextInt(8)+1;
			//步数清零
			step=0;
			//先打乱数据
			initData();
			//在加载图片
			initPicture();
		}
		else if(obj==sportItem)
		{
			path="..\\project\\image\\sport\\sport";
			sum=r.nextInt(10)+1;
			//步数清零
			step=0;
			//先打乱数据
			initData();
			//在加载图片
			initPicture();
		}
	}
}
