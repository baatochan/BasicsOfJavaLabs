import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

/**
 * Program: RoomCollectionComparisonApp - Porównaj kolekcje
 * Program ukazujący podobieństwa i różnice pomiędzy różnymi typami kolekcji zawierającymi niestandatowy typ danych.
 *
 * Klasa: RoomCollectionView - WidokKolekcji
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 11 grudnia 2016r.
 *
 * Created by barto on 11.12.16.
 */

public class RoomCollectionView extends JScrollPane {
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private DefaultTableModel tableModel;
	private Collection<Room> collection;
	
	RoomCollectionView(Collection<Room> collection, String title) {
		String[] kolumny = {"Budynek:", "Pokój:", "Opis:"};
		tableModel = new DefaultTableModel(kolumny, 0);
		table = new JTable(tableModel);
		this.collection = collection;
		setViewportView(table);
		setPreferredSize(new Dimension(150, 200));
		setBorder(BorderFactory.createTitledBorder(title));
	}
	
	void refresh() {
		tableModel.setRowCount(0);
		for (Room room : collection) {
			String[] row = {room.getBuildingName(), String.valueOf(room.getRoomNumber()), room.getDescription()};
			tableModel.addRow(row);
		}
	}
}
