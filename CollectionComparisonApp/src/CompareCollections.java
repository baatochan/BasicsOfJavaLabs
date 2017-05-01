import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Program: CollectionComparisonApp - Porównaj kolekcje
 * Program ukazujący podobieństwa i różnice pomiędzy różnymi typami kolekcji.
 *
 * Klasa: CompareCollections - PorownajKolekcje
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 11 grudnia 2016r.
 *
 * Created by barto on 11.12.16.
 */

public class CompareCollections extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Vector<String> vector = new Vector<String>();
	private ArrayList<String> arrayList = new ArrayList<String>();
	private LinkedList<String> linkedList = new LinkedList<String>();
	private HashSet<String> hashSet = new HashSet<String>();
	private TreeSet<String> treeSet = new TreeSet<String>();
	
	private CollectionView vectorView = new CollectionView(vector, "Vector");
	private CollectionView arrayListView = new CollectionView(arrayList, "ArrayList");
	private CollectionView linkedListView = new CollectionView(linkedList, "LinkedList");
	private CollectionView hashSetView = new CollectionView(hashSet, "HashSet");
	private CollectionView treeSetView = new CollectionView(treeSet, "TreeSet");
	
	private JLabel entryLabel = new JLabel("Tytuł: ");
	private JTextField entryField = new JTextField(10);
	private JButton addButton = new JButton("Dodaj");
	private JButton removeButton = new JButton("Usuń");
	private JButton clearButton = new JButton("Wyczyść");
	private JButton aboutButton = new JButton("O programie");
	
	public CompareCollections() {
		super("Porównanie działania kolekcji");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension windowSize = new Dimension(350, 540);
		setSize(windowSize);
		
		JPanel wrapperPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel listPanel = new JPanel();
		JPanel setPanel = new JPanel();
		topPanel.add(entryLabel);
		topPanel.add(entryField);
		
		wrapperPanel.setPreferredSize(windowSize);
		topPanel.setPreferredSize(new Dimension(350,65));
		
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
		if (source == addButton){
			if(!entryField.getText().equals("")) {
				vector.add(entryField.getText());
				arrayList.add(entryField.getText());
				linkedList.add(entryField.getText());
				hashSet.add(entryField.getText());
				treeSet.add(entryField.getText());
			}
		}
		
		else if (source == removeButton) {
			vector.remove(entryField.getText());
			arrayList.remove(entryField.getText());
			linkedList.remove(entryField.getText());
			hashSet.remove(entryField.getText());
			treeSet.remove(entryField.getText());
		}
		
		else if (source == clearButton) {
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
		
		vectorView.refresh();
		arrayListView.refresh();
		linkedListView.refresh();
		hashSetView.refresh();
		treeSetView.refresh();
	}

	public static void main (String[] args) {
		new CompareCollections();
	}
}
