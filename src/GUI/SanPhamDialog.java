package GUI;

import DAO.DonViTinhDAO;
import DAO.LoaiHangDAO;
import DTO.DonViTinhDTO;
import DTO.LoaiHangDTO;
import component.ButtonCustom;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import component.HeaderTitle;
import component.InputForm;
import component.InputImage;
import component.SelectForm;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Tran Nhat Sinh
 */
public class SanPhamDialog extends JDialog implements MouseListener {
    
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom, pnCenter, pnmainright;
    private ButtonCustom btnThemSanPham, btnCapNhat, btnHuyBo;
    InputForm tenSP, xuatxu, gianhap, giaban;
    SelectForm donvitinh, loaihang, khuvuc;
    InputImage hinhanh;
    JTextField idSP;
    GUI.SanPham jpSP;

    public String[] getdonvitinh() {
        ArrayList<DonViTinhDTO> dvt = DonViTinhDAO.getInstance().selectAll();
        String tenDVT[] = new String[dvt.size()];
        for (int i = 0; i < dvt.size(); i++) {
            tenDVT[i] = dvt.get(i).getTenDVT();
        }
        return tenDVT;
    }

    public String[] getloaihang() {
        ArrayList<LoaiHangDTO> lh = LoaiHangDAO.getInstance().selectAll();
        String tenLH[] = new String[lh.size()];
        for (int i = 0; i < lh.size(); i++) {
            tenLH[i] = lh.get(i).getTenloaihang();
        }
        return tenLH;
    }

    public void initComponents(String type) {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Thêm sản phẩm");
        this.setSize(new Dimension(870, 410));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle("THÊM SẢN PHẨM MỚI");
        pnCenter = new JPanel(new GridLayout(1, 2));
        pnmain = new JPanel(new GridLayout(3, 2, 0, 0));
        pnmain.setBackground(Color.WHITE);
        pnCenter.add(pnmain);
        
        pnmainright = new JPanel();
        pnmain.setBackground(Color.WHITE);
        pnCenter.add(pnmainright);
        
        tenSP = new InputForm("Tên Sản Phẩm");
        pnmain.add(tenSP);
        xuatxu = new InputForm("Xuất xứ");
        pnmain.add(xuatxu);
        gianhap = new InputForm("Giá nhập");
        pnmain.add(gianhap);
        giaban = new InputForm("Giá bán");
        pnmain.add(giaban);
        donvitinh = new SelectForm("Đơn vị tính", getdonvitinh());
        pnmain.add(donvitinh);
        loaihang = new SelectForm("Thuộc loại hàng", getloaihang());
        pnmain.add(loaihang);
        String tempKVK[] = {"khu A", "khuB", "khu C"};
        khuvuc = new SelectForm("Khu vực kho", tempKVK);
        pnmain.add(khuvuc);
        
        hinhanh = new InputImage("Hình minh họa");
        pnmainright.add(hinhanh);
        
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThemSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        
        pnbottom.add(btnThemSanPham);
        pnbottom.add(btnCapNhat);
        pnbottom.add(btnHuyBo);
        
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    public void initComponents(String title, String type) {
        this.setSize(new Dimension(870, 410));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnCenter = new JPanel(new GridLayout(1, 2));
        pnmain = new JPanel(new GridLayout(3, 2, 0, 0));
        pnmain.setBackground(Color.WHITE);
        pnCenter.add(pnmain);
        
        pnmainright = new JPanel();
        pnmainright.setBackground(Color.WHITE);
        pnCenter.add(pnmainright);
        
        pnmain.add(tenSP);
        pnmain.add(xuatxu);
        pnmain.add(gianhap);
        pnmain.add(giaban);
        pnmain.add(donvitinh);
        pnmain.add(loaihang);
        pnmain.add(khuvuc);
        
        pnmainright.add(hinhanh);
        
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThemSanPham = new ButtonCustom("Thêm Sản Phẩm", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThemSanPham.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);
        
        switch (type) {
            case "create" ->
                pnbottom.add(btnThemSanPham);
            case "update" ->
                pnbottom.add(btnCapNhat);
            case "view" ->
                tenSP.setDisable();
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    public SanPhamDialog(String type) {
        initComponents(type);
        switch (type) {
            case "create" -> {
                this.btnThemSanPham.setVisible(true);
                this.btnCapNhat.setVisible(false);
            }
            case "update" -> {
                this.btnThemSanPham.setVisible(false);
                this.btnCapNhat.setVisible(true);
                // Set thông tin sản phẩm lên form
            }
            default ->
                throw new AssertionError();
        }
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpSP = jpSP;
        tenSP = new InputForm("Tên sản phẩm");
        idSP = new JTextField("");
        xuatxu = new InputForm("Xuất xứ");
        gianhap = new InputForm("Giá nhập");
        giaban = new InputForm("Giá bán");
        donvitinh = new SelectForm("Đơn vị tính", getdonvitinh());
        loaihang = new SelectForm("Loại hàng", getloaihang());
        String tempKVK[] = {"khu A", "khuB", "khu C"};
        khuvuc = new SelectForm("Khu vực kho", tempKVK);
        hinhanh = new InputImage("Hình minh họa");
        initComponents(title, type);
    }
    
    public SanPhamDialog(GUI.SanPham jpSP, JFrame owner, String title, boolean modal, String type, DTO.SanPham sp) {
        super(owner, title, modal);
        tenSP = new InputForm("Tên sản phẩm");
        idSP = new JTextField("");
        xuatxu = new InputForm("Xuất xứ");
        gianhap = new InputForm("Giá nhập");
        giaban = new InputForm("Giá bán");
        setTenSP(sp.getTensp());
        setIdSP(Integer.toString(sp.getMasp()));
        donvitinh = new SelectForm("Đơn vị tính", getdonvitinh());
        loaihang = new SelectForm("Loại hàng", getloaihang());
        String tempKVK[] = {"khu A", "khuB", "khu C"};
        khuvuc = new SelectForm("Khu vực kho", tempKVK);
        hinhanh = new InputImage("Hình minh họa");
        this.jpSP = jpSP;
        initComponents(title, type);
    }
    
    public JPanel getPnmain() {
        return pnmain;
    }
    
    public void setPnmain(JPanel pnmain) {
        this.pnmain = pnmain;
    }
    
    public JPanel getPnbottom() {
        return pnbottom;
    }
    
    public void setPnbottom(JPanel pnbottom) {
        this.pnbottom = pnbottom;
    }
    
    public InputForm getTenSP() {
        return tenSP;
    }
    
    public void setTenSP(String text) {
        tenSP.setText(text);
    }
    
    public InputForm getXuatxu() {
        return xuatxu;
    }
    
    public void setXuatxu(String text) {
        xuatxu.setText(text);
    }
    
    public InputForm getGianhap() {
        return gianhap;
    }
    
    public void setGianhap(String text) {
        gianhap.setText(text);
    }
    
    public InputForm getGiaban() {
        return giaban;
    }
    
    public void setGiaban(String text) {
        giaban.setText(text);
    }

    public SelectForm getDonvitinh() {
        return donvitinh;
    }
    
    public void setDonvitinh(SelectForm donvitinh) {
        this.donvitinh = donvitinh;
    }
    
    public SelectForm getLoaihang() {
        return loaihang;
    }

    public void setLoaihang(SelectForm loaihang) {
        this.loaihang = loaihang;
    }
    
    public SelectForm getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(SelectForm khuvuc) {
        this.khuvuc = khuvuc;
    }
    
    public JTextField getIdSP() {
        return idSP;
    }
    
    public void setIdSP(String text) {
        this.idSP.setText(text);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThemSanPham) {
            String tensp = this.tenSP.getText();
            String xuatxu = this.xuatxu.getText();
            double gianhap = Double.valueOf(this.gianhap.getText());
            double giaban = Double.valueOf(this.giaban.getText());
            String hinhanh = this.hinhanh.getUrl_img();
            int dvt = this.donvitinh.getSelectedIndex()+1;
            int maloaihang = loaihang.getSelectedIndex()+1;
            int makhuvuc = khuvuc.getSelectedIndex()+1;
            int id = 0;
            JOptionPane.showMessageDialog(rootPane, tensp+"\n"+xuatxu+"\n"+gianhap+"\n"+giaban+"\n"+hinhanh+"\n"+dvt+"\n"
                    + maloaihang+"\n"+makhuvuc);
            DTO.SanPham sp = new DTO.SanPham(id, tensp, xuatxu, gianhap, giaban, hinhanh, dvt, maloaihang, makhuvuc);
            jpSP.spBUS.add(sp);
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
//            
//            Sa
//            jpSP.spBUS.update(sp);
//            jpSP.loadDataTalbe(jpSP.listSP);
//            dispose();
        }
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
