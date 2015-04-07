package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.cardType;

public class MakeAnAccusation extends JFrame{

	
	private ClueGame game;
	private Board board;
	private JButton makeGuess = new JButton("Make an Accusation");
	private JButton cancel = new JButton("Cancel"); 
	private Card roomGuess, personGuess, weaponGuess;
	private JComboBox<String> people, weapons, rooms;
	
	public MakeAnAccusation(Board board, ClueGame game){
		this.game = game;
		this.board = board;
		setSize(400,400);
		setLayout(new GridLayout(4,0));
		people = createGuessCombo(game.getPeople());
		people.addActionListener(new ComboListener());
		weapons = createGuessCombo(game.getWeapons());
		weapons.addActionListener(new ComboListener());
		rooms = createRoomCombo(game.getTestDeck());
		rooms.addActionListener(new ComboListener());
		roomGuess = new Card("Kitchen", cardType.ROOM);
		personGuess = new Card("Tupac", cardType.PERSON);
		weaponGuess = new Card("Shank", cardType.WEAPON);
		add(generateCombo("Room Guess", rooms));
		add(generateCombo("Person Guess", people));
		add(generateCombo("WeaponGuess", weapons));
		add(createButtonPanel());
	}
	
	private JPanel generateCombo (String s, JComboBox<String> combo) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), s));
		panel.add(combo);
		return panel;
	}
	
	private JComboBox<String> createGuessCombo(ArrayList<Card> cards) {
		JComboBox<String> combo = new JComboBox<String>();
		for(Card c: cards) {
			combo.addItem(c.getName());
		}
		return combo;
	}
	
	private JComboBox<String> createRoomCombo(ArrayList<Card> cards) {
		JComboBox<String> combo = new JComboBox<String>();
		for(Card c: cards) {
			if (c.getcardType() == cardType.ROOM){
				combo.addItem(c.getName());
			}
		}
		return combo;
	}
	
	public JPanel createButtonPanel() {
		makeGuess.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,0));
		buttonPanel.add(makeGuess);
		buttonPanel.add(cancel);
		return buttonPanel;
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
			if (e.getSource() == makeGuess) {
				Solution s = new Solution();
				s.setPerson(personGuess.getName());
				s.setWeapon(weaponGuess.getName());
				s.setPlace(roomGuess.getName());
				System.out.println(s.getPerson() +" "+s.getPlace()+" "+s.getWeapon());
				if (game.checkAccusation(s)) {
					JOptionPane.showMessageDialog(game, "YOU WIN! Answer was: "+s.getPerson()+" "+ 
							s.getPlace()+" "+s.getWeapon(),"Accusation Result", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(game, "You are a failure: "+s.getPerson()+" "+ 
							s.getPlace()+" "+s.getWeapon()+" was not correct"
							,"Accusation Result", JOptionPane.INFORMATION_MESSAGE);
				}
				setVisible(false);
				board.setHasMoved(true);
			}
			else if (e.getSource() == cancel) {
				setVisible(false);
			}
		}
	}
	
	private class ComboListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == people) {
				personGuess = new Card(people.getSelectedItem().toString(), cardType.PERSON);
				
			} else if (e.getSource() == weapons) {
				weaponGuess = new Card(weapons.getSelectedItem().toString(),cardType.WEAPON);
			} else if (e.getSource() == rooms) {
				roomGuess = new Card(rooms.getSelectedItem().toString(),cardType.ROOM);
			}
			
			
		}
	}
}
