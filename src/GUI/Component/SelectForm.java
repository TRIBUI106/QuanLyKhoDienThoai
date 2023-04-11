/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class SelectForm extends JPanel{
    private JLabel lblTitle;
    private JComboBox cbb;
    
    public SelectForm(String title, String[] obj) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        lblTitle = new JLabel(title);
        cbb = new JComboBox(obj);
        
        this.add(lblTitle);
        this.add(cbb);
    }
    
    public String getValue() {
        return (String) cbb.getSelectedItem();
    }
    
    public Object getSelectedItem() {
        return cbb.getSelectedItem();
    }
    
    public int getSelectedIndex() {
        return cbb.getSelectedIndex();
    }
    
    public void setSelectedIndex(int i) {
        cbb.setSelectedIndex(i);
    }
    
    public void setSelectedItem(Object a) {
        cbb.setSelectedItem(a);
    }
}
