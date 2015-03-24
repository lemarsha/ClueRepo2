package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JFrame {
	private JTextField name;

	public ControlGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(576, 250);
		JPanel panel = PlayerPanel();
		panel.setPreferredSize(new Dimension(700, 125));
		add(panel, BorderLayout.NORTH);
		panel = createDicePanel();
		add(panel, BorderLayout.WEST);
		panel = createGuessPanel();
		add(panel, BorderLayout.CENTER);
		panel = createGuessResultPanel();
		add(panel, BorderLayout.EAST);
	}

	 private JPanel PlayerPanel() {
		 	JPanel panel = new JPanel();
			name = new JTextField(20);
			JButton nextPlayer = new JButton("Next Player");
			JButton disagree = new JButton("Make an Accusation");
			panel.setLayout(new GridLayout(1, 3));
			panel.add(name);
			panel.add(nextPlayer);
			panel.add(disagree);
			panel.setBorder(new TitledBorder (new EtchedBorder(), "Whose turn?"));
			return panel;
	}
	 
	private JPanel createDicePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Roll");
		name = new JTextField(10);
		name.setEditable(false);
		panel.add(label);
		panel.add(name);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		return panel;
	}
	
	private JPanel createGuessPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Guess");
		name = new JTextField(10);
		name.setEditable(false);
		panel.add(label);
		panel.add(name);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		return panel;
	}
	
	private JPanel createGuessResultPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Response");
		name = new JTextField(10);
		name.setEditable(false);
		panel.add(label);
		panel.add(name);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		return panel;
	}
	
	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();	
		gui.setVisible(true);
	}


}