package BL;

import java.util.Collections;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import com.dotPycsLib.Modelclasses.*;

public class TableModelUebersichtFrei extends AbstractTableModel {

    private final String[] spalten = {"Name", "Klasse", "Führt für Abteilung", "Frei seit"};
    private LinkedList<Guide> listeFrei = new LinkedList<>();
    private LinkedList<Guide> listeFreiFilter = new LinkedList<>();

    @Override
    public int getRowCount() {
        return listeFreiFilter.size();
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
        Guide g = listeFreiFilter.get(rowIndex);
        
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
        if (!listeFrei.contains(g)) {
            listeFrei.add(g);
        }
        Collections.sort(listeFrei, new GuideComparer());
        listeFreiFilter = (LinkedList<Guide>) listeFrei.clone();
        fireTableRowsInserted(0, listeFreiFilter.size());
    }

    public String getGuideNameinSpecificRow(int row) {
        return listeFreiFilter.get(row).getFirst_name() + " " + listeFreiFilter.get(row).getLast_name();
    }

    public String getGuideTelNoInSpecificRow(int row) {
        return listeFreiFilter.get(row).getTel_no();
    }

    public void filterListe(String department) 
    {
        listeFreiFilter.clear();
        for (Guide g : listeFrei) {
            
            System.out.println("g-DEPT: "+g.getDept());
            System.out.println("dept: "+department);
            
            if (department.equals("MECHA/AUT") && g.getDept().equals("MECHA/AUT") && !listeFreiFilter.contains(g))
            {
                listeFreiFilter.add(g);
            } 
            else if (department.equals("EDV") && g.getDept().equals("EDV") && !listeFreiFilter.contains(g))
            {
                listeFreiFilter.add(g);
            }
            else if(department.equals("all"))
            {
                listeFreiFilter = listeFrei;
            }
            
        }
        Collections.sort(listeFreiFilter, new GuideComparer());
        this.fireTableRowsUpdated(0, listeFreiFilter.size());
    }
}
