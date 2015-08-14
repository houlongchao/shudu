package hlc.shudu.ui;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class SelectNumFrame extends JDialog implements MouseListener {
	private ShuduCell cell;
	
	public void setCell(ShuduCell cell) {
		this.cell = cell;
	}

	public SelectNumFrame(){
		//隐藏界面上面的工具栏
		this.setUndecorated(true);
		this.setSize(150, 150);
		this.setBackground(new Color(255,204,153, 123));
		this.setLayout(null);
		addNum();
	}
	//添加数字1~9
	private void addNum() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton btn = new JButton();
				btn.setSize(50, 50);
				btn.setLocation(i*50,j*50);
				btn.setText(""+(j*3+i+1));
				btn.addMouseListener(this);
				this.add(btn);
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int modes = e.getModifiers();
		if ((modes & InputEvent.BUTTON1_MASK) != 0) {
			JButton btn = (JButton) e.getSource();
			cell.setText(btn.getText());
		}
		this.dispose();
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
