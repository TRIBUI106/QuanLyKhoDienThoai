/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;
import BUS.ThuongHieuBUS;
import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author 84907
 */
public class ThuongHieuDialog extends JFrame implements MouseListener{
    HeaderTitle headTite;
    JPanel top, main, bottom;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    JButton add, del, update;
    ThuongHieuBUS thBUS = new ThuongHieuBUS();
    ArrayList<ThuongHieuDTO> list = thBUS.getAll();

    public ThuongHieuDialog() {
        //super(owner, title, modal);
        initComponent();
        loadDataTable(list);
    }

    public void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));        
        headTite = new HeaderTitle("Quản lý thương hiệu sản phẩm");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();
        
        ms=new InputForm("Tên thương hiệu");
        ms.setPreferredSize(new Dimension(150,70));
        top.setLayout(new FlowLayout(0));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 150));
        top.add(headTite);
        top.add(ms);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420,200));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane();
        scrollTable.setBackground(Color.WHITE);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã màu", "Tên màu"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        main.add(scrollTable);
        
        add = new JButton("Thêm");
        add.addMouseListener(this);
        del = new JButton("Xóa");
        del.addMouseListener(this);
        update = new JButton("Sửa");
        update.addMouseListener(this);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1,50,20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0,70));
        
        this.add(top,BorderLayout.NORTH);
        this.add(main,BorderLayout.CENTER);
        this.add(bottom,BorderLayout.SOUTH);
                this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<ThuongHieuDTO> result) {
        tblModel.setRowCount(0);
        for (ThuongHieuDTO th : result) {
            tblModel.addRow(new Object[]{
                th.getMathuonghieu(),th.getTenthuonghieu()
            });
        }
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
       new ThuongHieuDialog().setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource()==add){
            if(this.ms.getText()==""){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thương hiệu mới");
            }
            else{
                int id = ThuongHieuDAO.getInstance().getAutoIncrement();
                String tenthuonghieu= ms.getText();
                thBUS.add(tenthuonghieu);
                loadDataTable(list);
                ms.setText("");
        }
    }
        else if(e.getSource()==del){
            int index = getRowSelected();
            if(index!=-1){
                 thBUS.delete(list.get(index));
                 loadDataTable(list);
                 ms.setText("");
                 }
        }
        else if(e.getSource()==update){
            int index = getRowSelected();
            if(index!=-1){
                String tenthuonghieu= ms.getText();
                 thBUS.update(new ThuongHieuDTO(list.get(index).getMathuonghieu(),tenthuonghieu));
                 loadDataTable(list);
                 ms.setText("");
                 }
        }
        else if(e.getSource()==table){
            int index = table.getSelectedRow();
            ms.setText(list.get(index).getTenthuonghieu());
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thương hiệu!");
        }
        return index;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
