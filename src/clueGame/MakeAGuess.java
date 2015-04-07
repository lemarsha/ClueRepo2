package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.cardType;

public class MakeAGuess extends JFrame{

	private ClueGame game;
	private Board board;
	private JTextField currentRoom = new JTextField(10);
	private String room;
	private JButton makeGuess = new JButton("Make a Guess");
	private JButton cancel = new JButton("Cancel"); 
	private Card roomGuess, personGuess, weaponGuess;
	private JComboBox<String> people, weapons;
	private Solution guessArray;
	private ArrayList<Player> players;
	
	public MakeAGuess(Board board, ClueGame game, String room){
		this.game = game;
		this.board = board;
		this.room = room;
		players = game.getPlayers();
		setSize(400,400);
		setLayout(new GridLayout(4,0));
		people = createGuessCombo(game.getPeople());
		people.addActionListener(new ComboListener());
		weapons = createGuessCombo(game.getWeapons());
		weapons.addActionListener(new ComboListener());
		roomGuess = new Card("Kitchen", cardType.ROOM);
		personGuess = new Card("Tupac", cardType.PERSON);
		weaponGuess = new Card("Shank", cardType.WEAPON);
		add(roomPanel(room, "Room Guess"));
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
	
	private JPanel roomPanel(String room, String s) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),s));
		currentRoom.setText(room);
		panel.add(currentRoom);
		currentRoom.setEditable(false);
		return panel;
	}
	
	public void setRoom(String room) {
		this.room = room;
		currentRoom.setText(room);
		roomGuess = new Card(room, cardType.ROOM);
	}
	
	public void updateGuessDisplay(String room) {
		setRoom(room);
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
				guessArray = new Solution();
				guessArray.setPlace(roomGuess.getName());
				guessArray.setPerson(personGuess.getName());
				guessArray.setWeapon(weaponGuess.getName());
				Card disproved = game.handleSuggestion(guessArray.getPerson(), guessArray.getPlace(), 
						guessArray.getWeapon(), game.getPlayers().get(0));
				ControlGUI controlPanel = game.getControlGUI();
				controlPanel.updateGuessDisplay(guessArray, disproved);
				String accusedPlayer = guessArray.getPerson();
				for (Player p: players) {
					if (p.getName().equals(accusedPlayer)) {
						p.setLocation(players.get(0).getLocation().getRow(), players.get(0).getLocation().getColumn());
						break;
					}
				}
				board.repaint();
				setVisible(false);
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
			}
			
			
		}
	}
	
}
