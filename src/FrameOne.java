import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameOne extends JFrame {
	JFrame frameOne;
	public void frameOne() {
		frameOne = new JFrame();
		frameOne.setTitle("Information");
		frameOne.setBounds(600, 300, 200, 120);
		frameOne.setVisible(true);
		frameOne.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
	}
}