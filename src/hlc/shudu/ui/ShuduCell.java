package hlc.shudu.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ShuduCell extends JButton {
	public ShuduCell(){
		this.setSize(50,50);
		Font font = new Font("",2,24);
		this.setFont(font);
		this.setBackground(new Color(255,153,102));
		this.setForeground(Color.BLUE);
	}
}
