package maze_package;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class GUI implements ActionListener{
	private int count=0;
	private JLabel label= new JLabel();
	private JFrame frame = new JFrame();
	private JPanel panel =new JPanel();
	private JButton button=new JButton();
	
	public void GUI()
	{
			
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));	
		panel.setLayout(new GridLayout());
		panel.add(button);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Maze");
		frame.pack();
		frame.setVisible(true);
		
		button.setText("Create New Maze");
		button.addActionListener(this);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Maze Number: " + count);
		
		
	}
	 
}
