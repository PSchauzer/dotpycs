/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Corinna
 */
public class TableRendererUebersichtFrei implements TableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        JLabel label = new JLabel(value.toString());
        //label.setFont(new Font("Arial", Font.PLAIN, 14));
       // label.setBackground(Color.white);

//        if (isSelected && table.hasFocus()) {
//            label.setForeground(new Color(0,128,0));
//        }
//        
        //label.setBorder(new CompoundBorder(new EmptyBorder(new Insets(1, 4, 1, 4)), label.getBorder()));
        
        label.setOpaque(true);
        
        if(isSelected)
        {
            label.setBackground(Color.red);
        }
        return label;
    }
}
