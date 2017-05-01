import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Program: RoomCollectionComparisonApp - Porównaj kolekcje
 * Program ukazujący podobieństwa i różnice pomiędzy różnymi typami kolekcji zawierającymi niestandatowy typ danych.
 *
 * Klasa: RoomCompareCollections - PorownajKolekcje
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 11 grudnia 2016r.
 *
 * Created by barto on 11.12.16.
 */

public class RoomCompareCollections extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Vector<Room> vector = new Vector<Room>();
	private ArrayList<Room> arrayList = new ArrayList<Room>();
	private LinkedList<Room> linkedList = new LinkedList<Room>();
	private HashSet<Room> hashSet = new HashSet<Room>();
	private TreeSet<Room> treeSet = new TreeSet<Room>();
	
	private RoomCollectionView vectorView = new RoomCollectionView(vector, "Vector");
	private RoomCollectionView arrayListView = new RoomCollectionView(arrayList, "ArrayList");
	private RoomCollectionView linkedListView = new RoomCollectionView(linkedList, "LinkedList");
	private RoomCollectionView hashSetView = new RoomCollectionView(hashSet, "HashSet");
	private RoomCollectionView treeSetView = new RoomCollectionView(treeSet, "TreeSet");
	
	private JLabel buildingEntryLabel = new JLabel("Budynek: ");
	private JTextField buildingEntryField = new JTextField(10);
	private JLabel roomEntryLabel = new JLabel("Pokój: ");
	private JTextField roomEntryField = new JTextField(10);
	private JLabel descriptionEntryLabel = new JLabel("Opis: ");
	private JTextField descriptionEntryField = new JTextField(10);
	private JButton addButton = new JButton("Dodaj");
	private JButton removeButton = new JButton("Usuń");
	private JButton clearButton = new JButton("Wyczyść");
	private JButton aboutButton = new JButton("O programie");
	
	public RoomCompareCollections() {
		super("Porównanie działania kolekcji");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension windowSize = new Dimension(550, 540);
		setSize(windowSize);
		
		JPanel wrapperPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel listPanel = new JPanel();
		JPanel setPanel = new JPanel();
		topPanel.add(buildingEntryLabel);
		topPanel.add(buildingEntryField);
		topPanel.add(roomEntryLabel);
		topPanel.add(roomEntryField);
		topPanel.add(descriptionEntryLabel);
		topPanel.add(descriptionEntryField);
		
		wrapperPanel.setPreferredSize(windowSize);
		topPanel.setPreferredSize(new Dimension(500,65));
		
		addButton.addActionListener(this);
		topPanel.add(addButton);
		
		removeButton.addActionListener(this);
		topPanel.add(removeButton);
		
		clearButton.addActionListener(this);
		topPanel.add(clearButton);
		
		aboutButton.addActionListener(this);
		topPanel.add(aboutButton);
		
		listPanel.add(vectorView);
		listPanel.add(arrayListView);
		listPanel.add(linkedListView);
		
		setPanel.add(hashSetView);
		setPanel.add(treeSetView);
		
		wrapperPanel.add(topPanel);
		wrapperPanel.add(listPanel);
		wrapperPanel.add(setPanel);
		
		setContentPane(wrapperPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == clearButton) {
			vector.clear();
			arrayList.clear();
			linkedList.clear();
			hashSet.clear();
			treeSet.clear();
		}
		
		else if (source == aboutButton)
			JOptionPane.showMessageDialog(this,
					"Program: CollectionComparisonApp - Porównaj kolekcje\nAutor: Bartosz Rodziewicz, 226105\nData: 11 grudnia 2016r.",
					"O programie",1);
		
		else try {
			int roomNumber = Integer.parseInt(roomEntryField.getText());
			Room r = new Room(buildingEntryField.getText(),roomNumber,descriptionEntryField.getText());
			if (source == addButton){
				if(!buildingEntryField.getText().equals("") && !roomEntryField.getText().equals("")) {
					vector.add(r);
					arrayList.add(r);
					linkedList.add(r);
					hashSet.add(r);
					treeSet.add(r);
				}
			}
			
			else if (source == removeButton) {
				vector.remove(r);
				arrayList.remove(r);
				linkedList.remove(r);
				hashSet.remove(r);
				treeSet.remove(r);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Numer pokoju musi być numerem!", "Błąd", 0);
		}
		
		vectorView.refresh();
		arrayListView.refresh();
		linkedListView.refresh();
		hashSetView.refresh();
		treeSetView.refresh();
	}

	public static void main (String[] args) {
		new RoomCompareCollections();
	}
}
