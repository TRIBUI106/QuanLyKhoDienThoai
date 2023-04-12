package GUI.Component;

import BUS.TaiKhoanBUS;
import DAO.ChiTietQuyenDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.Panel.ChuyenKho;
import GUI.Panel.KhachHang;
import GUI.Panel.KhuVucKho;
import GUI.Panel.NhaCungCap;
import GUI.Panel.NhanVien;
import GUI.Panel.PhanQuyen;
import GUI.Panel.PhieuNhap;
import GUI.Panel.PhieuXuat;
import GUI.Panel.QuanLyThuocTinhSP;
import GUI.Panel.SanPham;
import GUI.Panel.TaiKhoan;
import GUI.Panel.ThuongHieu;
import GUI.Panel.TrangChu;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.apache.poi.util.LocaleID;
//import GUI.Component.itemTaskbar;

public class MenuTaskbar extends JPanel {

    TrangChu trangChu;
    SanPham sanPham;
    QuanLyThuocTinhSP quanLyThuocTinhSP;
    KhuVucKho quanLyKho;
    ChuyenKho chuyenKho;
    PhieuNhap phieuNhap;
    PhieuXuat phieuXuat;
    KhachHang khachHang;
    NhaCungCap nhacungcap;
    NhanVien nhanVien;
    TaiKhoan taiKhoan;
    PhanQuyen phanQuyen;
    String[][] getSt = {
        {"Trang chủ", "home_32px.svg", "trangchu"},
        {"Sản phẩm", "product_32px.svg", "trangchu"},
        {"Thuộc tính", "brand_32px.svg", "thuonghieu"},
        {"Khu vực kho", "area_32px.svg", "khuvuckho"},
        {"Kiểm kê", "inventory_32px.svg", "kiemke"},
        {"Phiếu nhập", "import_32px.svg", "phieunhap"},
        {"Phiếu xuất", "export_32px.svg", "phieuxuat"},
        {"Khách hàng", "customer_32px.svg", "khachang"},
        {"Nhà cung cấp", "supplier_32px.svg", "nhacungcap"},
        {"Nhân viên", "staff_32px.svg", "nhanvien"},
        {"Tài khoản", "account_32px.svg", "taikhoan"},
        {"Phân quyền", "permission_32px.svg", "phanquyen"},
        {"Đăng xuất", "log_out_32px.svg", "dangxuat"},};

    Main main;
    TaiKhoanDTO user;
    public itemTaskbar[] listitem;

    JLabel lblTenNhomQuyen, lblUsername;
    JScrollPane scrollPane;

    //tasbarMenu chia thành 3 phần chính là pnlCenter, pnlTop, pnlBottom
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(187, 222, 251);
    private ArrayList<ChiTietQuyenDTO> listQuyen;

    public MenuTaskbar(Main main) {
        this.main = main;
        initComponent();
    }

    public MenuTaskbar(Main main, TaiKhoanDTO tk) {
        this.main = main;
        this.user = tk;
        listQuyen = ChiTietQuyenDAO.getInstance().selectAll(tk.getManhomquyen() + "");
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[getSt.length];
        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));

        // bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(1, 10, 10));
        pnlTop.add(info, BorderLayout.CENTER);
        this.add(pnlTop, BorderLayout.NORTH);

        // Cái info này bỏ vô cho đẹp tí, mai mốt có gì xóa đi đê hiển thị thông tin tài khoản và quyền
        in4(info);

        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(250, 600));
        pnlCenter.setBackground(DefaultColor);
        pnlCenter.setLayout(new FlowLayout(0, 0, 0));

        bar3 = new JPanel();
        bar3.setBackground(new Color(204, 214, 219));
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);

        for (int i = 0; i < getSt.length; i++) {
            if (i + 1 == getSt.length) {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlBottom.add(listitem[i]);
            } else {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlCenter.add(listitem[i]);
//                if(!checkRole(getSt[i][2])) listitem[i].setVisible(false);
            }
        }

        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setForeground(HowerFontColor);

        for (int i = 0; i < getSt.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                trangChu = new TrangChu();
                main.setPanel(trangChu);
            }
        });

        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                sanPham = new SanPham();
                main.setPanel(sanPham);

            }
        });
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                quanLyThuocTinhSP = new QuanLyThuocTinhSP();
                main.setPanel(quanLyThuocTinhSP);
            }
        });
        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quanLyKho = new KhuVucKho();
                main.setPanel(quanLyKho);
            }
        });
        listitem[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                chuyenKho = new ChuyenKho();
                main.setPanel(chuyenKho);
            }
        });

        listitem[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuNhap = new PhieuNhap(main);
                main.setPanel(phieuNhap);
            }
        });
        listitem[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuXuat = new PhieuXuat(main);
                main.setPanel(phieuXuat);
            }
        });
        listitem[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                khachHang = new KhachHang();
                main.setPanel(khachHang);
            }
        });
        listitem[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhacungcap = new NhaCungCap();
                main.setPanel(nhacungcap);
            }
        });

        listitem[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhanVien = new NhanVien();
                main.setPanel(nhanVien);
            }
        });
        listitem[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                taiKhoan = new TaiKhoan();
                main.setPanel(taiKhoan);
            }
        });
        listitem[11].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phanQuyen = new PhanQuyen();
                main.setPanel(phanQuyen);
            }
        });
    }

    public boolean checkRole(String s) {
        boolean check = false;
        for (int i = 0; i < listQuyen.size(); i++) {
            if (listQuyen.get(i).getHanhdong().equals("view")) {
                if (s.equals(listQuyen.get(i).getMachucnang())) {
                    check = true;
                }
            }
        }
        return check;
    }

    public void pnlMenuTaskbarMousePress(MouseEvent evt) {
        for (int i = 0; i < getSt.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(DefaultColor);
                listitem[i].setForeground(FontColor);
            }
        }
    }

    public void in4(JPanel info) {
        //        NhomQuyenDTO nhomQuyen = NhomQuyenDAO.getInstance().selectById(Integer.toString(user.getManhomquyen()));
//        lblTenNhomQuyen = new JLabel(nhomQuyen.getTennhomquyen());
        JPanel pnl1 = new JPanel();
        pnl1.setPreferredSize(new Dimension(190, 30));
        pnl1.setOpaque(false);
        info.add(pnl1);
        lblTenNhomQuyen = new JLabel("Admin");
        lblTenNhomQuyen.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnl1.add(lblTenNhomQuyen);

        JPanel pnl2 = new JPanel();
        pnl2.setPreferredSize(new Dimension(190, 30));
        pnl2.setOpaque(false);
        pnl2.setLayout(new FlowLayout(0,0,0));
        info.add(pnl2);

        JLabel lbl2 = new JLabel("Username: ");
        lbl2.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnl2.add(lbl2);
        lblUsername = new JLabel("NhatSinh");
        lblUsername.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
//        lblUsername = new JLabel(user.getUsername());
        pnl2.add(lblUsername);
    }
}
