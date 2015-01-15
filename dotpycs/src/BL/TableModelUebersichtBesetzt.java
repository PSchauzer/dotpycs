package BL;

import java.util.Collections;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import com.dotPycsLib.Modelclasses.*;

public class TableModelUebersichtBesetzt extends AbstractTableModel {

    private final String[] spalten = {"Name", "Klasse", "Führt für Abteilung", "Besetzt seit"};
    private LinkedList<Guide> listeBesetzt = new LinkedList<>();
    private LinkedList<Guide> listeBesetztFilter = new LinkedList<>();

    @Override
    public int getRowCount() {
        return listeBesetztFilter.size();
    }

    @Override
    public String getColumnName(int column) {
        return spalten[column];
    }

    @Override
    public int getColumnCount() {
        return spalten.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Guide g = listeBesetztFilter.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return g.getFirst_name() + " " + g.getLast_name();
            case 1:
                return g.getGuide_class();
            case 2:
                return g.getDept();
            case 3:
                return 5;
            default:
                return "error";
        }
    }

    public void addGuide(Guide g) {
        if (!listeBesetzt.contains(g)) {
            listeBesetzt.add(g);
        }
        Collections.sort(listeBesetzt, new GuideComparer());
        listeBesetztFilter = (LinkedList<Guide>) listeBesetzt.clone();
        fireTableRowsInserted(0, listeBesetztFilter.size());
    }

    public void filterListe(String department) {
        listeBesetztFilter.clear();
        for (Guide g : listeBesetzt) {
            

            if (department.equals("MECHA/AUT") && g.getDept().equals("MECHA/AUT") && !listeBesetztFilter.contains(g))
            {
                listeBesetztFilter.add(g);
            } 
            else if (department.equals("EDV") && g.getDept().equals("EDV") && !listeBesetztFilter.contains(g))
            {
                listeBesetztFilter.add(g);
            }
            else if(department.equals("all"))
            {
                listeBesetztFilter = (LinkedList<Guide>) listeBesetzt.clone();
            }
            
        }
        Collections.sort(listeBesetztFilter, new GuideComparer());
        this.fireTableRowsUpdated(0, listeBesetztFilter.size());
    }

}
