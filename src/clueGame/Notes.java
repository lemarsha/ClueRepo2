package clueGame;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Notes extends JDialog{
	
	public Notes(ArrayList<Card> people, ArrayList<Card> weps, ArrayList<Card> locs){
		setSize(400,600);
		setLayout(new GridLayout(3,2));
		add(generateCheckBox(people, "People"));
		add(generateCombo(people, "Person Guess"));
		add(generateCheckBox(locs, "Rooms"));
		add(generateCombo(locs, "Room Guess"));
		add(generateCheckBox(weps, "Weapons"));
		add(generateCombo(weps, "Weapon Guess"));
	}
	
	private JPanel generateCombo (ArrayList<Card> cards, String s) {
		JComboBox<String> combo = new JComboBox<String>();
		JPanel panel = new JPanel();
		for(Card c: cards) {
			combo.addItem(c.getName());
		}
		combo.addItem("Unsure");
		panel.setBorder(new TitledBorder (new EtchedBorder(), s));
		panel.add(combo);
		return panel;
	}
	
	private JPanel generateCheckBox(ArrayList<Card> cards, String s){
		JPanel panel = new JPanel();
		JCheckBox cb;
		for(Card c: cards) {
			cb = new JCheckBox(c.getName());
			panel.add(cb);
		}
		panel.setBorder(new TitledBorder (new EtchedBorder(), s));
		return panel;
	}
	
}
