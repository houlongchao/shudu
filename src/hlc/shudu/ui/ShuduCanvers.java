package hlc.shudu.ui;

import hlc.shudu.src.ShuduHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ShuduCanvers extends JPanel implements MouseListener {
	ShuduCell[][] cells;
	// 得到数独数组
	int[][] maps = new int[9][9];
	private SelectNumFrame selectNum;

	/*
	 * 默认构造函数
	 */
	public ShuduCanvers() {
		ShuduMainFrame.usedTime = 0;
		maps = ShuduHelper.getMap();
		// 加载数独区
		this.setLayout(null);
		cells = new ShuduCell[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// this.remove(cells[i][j]);
				// 创建单元格
				cells[i][j] = new ShuduCell();
				// 设置位置
				cells[i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50
						+ (j / 3) * 5);
				if (passRole(ShuduMainFrame.pass)) {
					cells[i][j].setText("" + maps[i][j]);
					// 设置背景颜色
					cells[i][j].setBackground(getColor(maps[i][j]));
					cells[i][j].setEnabled(false);
					cells[i][j].setForeground(Color.gray);
				} else {
					cells[i][j].addMouseListener(this);
				}
				this.add(cells[i][j]);
			}
		}
		checkFinish();

		// reLoadCanvers();
	}

	/*
	 * 检查是否完成
	 */
	private void checkFinish() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
					if (!check(i, j)) {
						return;
					}
				}
			}
		
		// 停止用户用时计时器
		ShuduMainFrame.userTimeAction.stop();
		// 清除所有cell监听
		clearAllListener();
		// 闯关数加一
		ShuduMainFrame.pass += 1;
		if (ShuduMainFrame.pass > 10) {
			int o = JOptionPane
					.showConfirmDialog(this, "您已经通关了，是否重头开始？", "", 0);
			if (o == 1) {
				System.exit(0);
			} else {
				ShuduMainFrame.pass = 1;
			}
		} else {
			JOptionPane.showMessageDialog(this, "恭喜你通过本关！用时："
					+ ShuduMainFrame.usedTime + "秒\n即将进入下一关！");
		}
		// 更新关卡提示
		ShuduMainFrame.lbPass.setText("" + ShuduMainFrame.pass);
		// 开始新的关卡
		reLoadCanvers();
		// 打开用户用时计时器
		ShuduMainFrame.userTimeAction.start();

	}

	/*
	 * 检查指定坐标处的单元格
	 */

	private boolean check(int i, int j) {
		if (cells[i][j].getText().isEmpty()) {
			return false;
		}
		
		for (int k = 0; k < 9; k++) {
			if (cells[i][j].getText().trim().equals(cells[i][k].getText().trim()) && j!=k) {
				return false;
			}
			if (cells[i][j].getText().trim().equals(cells[k][j].getText().trim()) && i != k) {
				return false;
			}
			int ii = (i / 3) * 3 + k / 3;
			int jj = (j / 3) * 3 + k % 3;
			if (cells[i][j].getText().trim().equals(cells[ii][jj].getText().trim()) &&!(i == ii && j == jj)) {
				return false;
			}

		}
		return true;
	}

	/*
	 * 重新加载数独区
	 */
	public void reLoadCanvers() {
		ShuduMainFrame.usedTime = 0;
		maps = ShuduHelper.getMap();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.remove(cells[i][j]);
				// 创建单元格
				cells[i][j] = new ShuduCell();
				// 设置位置
				cells[i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50
						+ (j / 3) * 5);
				if (passRole(ShuduMainFrame.pass)) {
					cells[i][j].setText("" + maps[i][j]);
					// 设置背景颜色
					cells[i][j].setBackground(getColor(maps[i][j]));
					cells[i][j].setEnabled(false);
					cells[i][j].setForeground(Color.gray);
				} else {
					cells[i][j].addMouseListener(this);
				}
				this.add(cells[i][j]);
			}
		}
		this.repaint();
		checkFinish();

	}

	/*
	 * 根据关卡随机产生该位置是否显示数字
	 */
	private boolean passRole(int pass) {
		// TODO Auto-generated method stub
		return Math.random() * 11 > pass;
	}

	/*
	 * 根据数字获得颜色
	 */
	private Color getColor(int i) {
		Color color = Color.pink;
		switch (i) {
		case 1:
			color = new Color(255, 255, 204);
			break;
		case 2:
			color = new Color(204, 255, 255);
			break;
		case 3:
			color = new Color(255, 204, 204);
			break;
		case 4:
			color = new Color(255, 204, 153);
			break;
		case 5:
			color = new Color(204, 255, 153);
			break;
		case 6:
			color = new Color(204, 204, 204);
			break;
		case 7:
			color = new Color(255, 204, 204);
			break;
		case 8:
			color = new Color(255, 255, 255);
			break;
		case 9:
			color = new Color(153, 255, 153);
			break;
		default:
			break;
		}
		return color;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int modes = e.getModifiers();
		if ((modes & InputEvent.BUTTON3_MASK) != 0) {// 点击鼠标右键
			// 清空点击单元格上的内容
			((ShuduCell) e.getSource()).setText("");
		} else if ((modes & InputEvent.BUTTON1_MASK) != 0) {// 点击鼠标左键
			// 如果选择数字窗口存在则销毁
			if (selectNum != null) {
				selectNum.dispose();
			}
			// 新建一个选择窗口
			selectNum = new SelectNumFrame();
			// 设置成模态窗口
			selectNum.setModal(true);
			// 设置选择窗口在显示器上的位置
			selectNum.setLocation(e.getLocationOnScreen().x,
					e.getLocationOnScreen().y);
			// 将点击的单元格传递给数字选择窗口
			selectNum.setCell((ShuduCell) e.getSource());
			// 显示数字选择窗口
			selectNum.setVisible(true);
		}
		checkFinish();
	}

	/*
	 * 清除所有cell的点击监听
	 */
	private void clearAllListener() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].removeMouseListener(this);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
