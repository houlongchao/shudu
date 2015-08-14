package hlc.shudu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/*
 * 数独主窗体
 */
public class ShuduMainFrame extends JFrame {

	public static int pass = 1; // 关卡
	public static JLabel lbPass; // 显示关卡的lable
	public static long usedTime = 0; // 
	private ShuduCanvers panelCanvers; // 主游戏区
	public static Timer userTimeAction;

	/*
	 * 默认构造函数
	 */
	public ShuduMainFrame() {
		// 初始化方法
		init();
		// 添加组件
		addComponent();
		// 添加主游戏区
		addCanvers();

	}

	/*
	 * 添加主游戏区
	 */
	private void addCanvers() {
		panelCanvers = new ShuduCanvers();
		panelCanvers.setBorder(new TitledBorder("游戏区"));

		// 将主游戏区添加到窗体中
		this.add(panelCanvers, BorderLayout.CENTER);

	}

	/*
	 * 添加组件区
	 */
	private void addComponent() {
		JPanel panelComponent = new JPanel();
		// 添加消息区
		addPanelMsg(panelComponent);
		// 添加时间区
		addPanelTime(panelComponent);

		// 将组件添加到窗体顶部
		this.add(panelComponent, BorderLayout.NORTH);

	}

	private void addPanelTime(JPanel panelComponent) {
		JPanel panelTime = new JPanel();
		panelTime.setBorder(new TitledBorder("时间"));
		panelTime.setLayout(new GridLayout(2, 1));

		final JLabel lbSysTime = new JLabel();
		final JLabel lbUserTime = new JLabel();

		panelTime.add(lbSysTime, BorderLayout.NORTH);
		panelTime.add(lbUserTime, BorderLayout.SOUTH);

		// 设置系统时间定时器
		Timer sysTimeAction = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long timeMillis = System.currentTimeMillis();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				lbSysTime.setText("    系统时间：  " + df.format(timeMillis));
			}
		});
		sysTimeAction.start();
		userTimeAction = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lbUserTime.setText("    您已用时：  " + (++usedTime)+ " sec.");
			}
		});
		userTimeAction.start();

		panelComponent.add(panelTime, BorderLayout.EAST);

	}

	/*
	 * 添加消息区
	 */
	private void addPanelMsg(JPanel panelComponent) {
		// panelComponent.setBorder(new TitledBorder("消息区"));
		panelComponent.setLayout(new GridLayout(1, 3));
		Font font14 = new Font("", 4, 14);
		Font font28 = new Font("", 2, 28);

		JPanel panelMsg = new JPanel();
		panelMsg.setBorder(new TitledBorder("消息区"));

		JLabel lbPass1 = new JLabel("关卡：第");
		lbPass1.setFont(font14);
		panelMsg.add(lbPass1);

		// 显示关卡数
		lbPass = new JLabel("" + pass);
		lbPass.setForeground(Color.RED);
		lbPass.setFont(font28);
		panelMsg.add(lbPass);

		JLabel lbPass2 = new JLabel("关/总共10关");
		lbPass2.setFont(font14);
		panelMsg.add(lbPass2);

		
//		Icon icon = new ImageIcon("icon/load.png");
//		JButton btnReLoad = new JButton("变身", icon);
//		btnReLoad.setFont(font14);
//		// 隐藏按钮背景
//		btnReLoad.setContentAreaFilled(false);
//		// 取消按钮边框
//		btnReLoad.setBorderPainted(false);
//		btnReLoad.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				panelCanvers.reLoadCanvers();
//			}
//		});
//		// 将按钮添加到消息区
//		 panelMsg.add(btnReLoad);
		panelComponent.add(panelMsg, BorderLayout.CENTER);

	}

	/*
	 * 界面初始化
	 */
	private void init() {
		// 设置窗口初始大小
		this.setSize(515, 600);
		// 设置窗口初始位置
		this.setLocation(500, 50);
		// 设置窗口标题
		this.setTitle("数独游戏(By：侯龙超)");
		// 设置窗体不允许改变大小
		this.setResizable(false);
		// 设置默认关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
