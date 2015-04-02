package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JPanel {
	private JTextField name;
	private Board board;
	private JButton nextPlayer, makeAccusation;
	private ClueGame game;
	private JTextField diceRoll = new JTextField(10);
	private JTextField playerName = new JTextField(10);

	public ControlGUI(Board board, ClueGame game)
	{
		this.board = board;
		this.game = game;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setTitle("Clue Game");
		setLayout(new GridLayout(1,2));
		setSize(576, 100);
		JPanel panel = PlayerPanel();
		panel.setPreferredSize(new Dimension(700, 125));
		add(panel, BorderLayout.NORTH);

		JPanel p = new JPanel();
		panel = createDicePanel(game.getRoll());
		p.add(panel, BorderLayout.WEST);
		panel = createGuessPanel();
		p.add(panel, BorderLayout.CENTER);
		panel = createGuessResultPanel();
		p.add(panel, BorderLayout.EAST);
		add(p);
	}

	private JPanel PlayerPanel() {
		JPanel panel = new JPanel();
		name = new JTextField(20);
		nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ButtonListener());
		makeAccusation = new JButton("Make an Accusation");
		makeAccusation.addActionListener(new ButtonListener());
		panel.setLayout(new GridLayout(1, 3));
		panel.add(playerName);
		panel.add(nextPlayer);
		panel.add(makeAccusation);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Whose turn?"));
		return panel;
	}

	private JPanel createDicePanel(int roll) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Roll");
		diceRoll.setText("hi");
		diceRoll.setEditable(false);
		panel.add(label);
		panel.add(diceRoll);
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
	
	public void setRoll(int roll) {
		diceRoll.setText(""+roll);
	}
	
	public void setPlayerName(String playerName) {
		this.playerName.setText(playerName);
	}
	
	public void updateDisplay(int roll, String playerName) {
		setRoll(roll);
		setPlayerName(playerName);
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e){

			if (e.getSource() == nextPlayer) {
				game.nextPlayer();

			}
			else if (e.getSource() == makeAccusation) {

			}

		}
	}

}