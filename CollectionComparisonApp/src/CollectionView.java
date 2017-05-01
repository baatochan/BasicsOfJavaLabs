import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

/**
 * Program: CollectionComparisonApp - Porównaj kolekcje
 * Program ukazujący podobieństwa i różnice pomiędzy różnymi typami kolekcji.
 *
 * Klasa: CollectionView - WidokKolekcji
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 11 grudnia 2016r.
 *
 * Created by barto on 11.12.16.
 */

public class CollectionView extends JScrollPane {
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private DefaultTableModel tableModel;
	private Collection<String> collection;
	
	CollectionView(Collection<String> collection, String title) {
		String[] kolumny = {"Tytuł:"};
		tableModel = new DefaultTableModel(kolumny, 0);
		table = new JTable(tableModel);
		this.collection = collection;
		setViewportView(table);
		setPreferredSize(new Dimension(100, 200));
		setBorder(BorderFactory.createTitledBorder(title));
	}
	
	void refresh() {
		tableModel.setRowCount(0);
		for (String entry : collection) {
			String[] row = {entry};
			tableModel.addRow(row);
		}
	}
}
