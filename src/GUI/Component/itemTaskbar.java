package GUI.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

public class itemTaskbar extends JPanel implements MouseListener {

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    JLabel lblIcon, pnlContent, pnlSoLuong;
    JPanel right;
    InputImage1 img;
    public boolean isSelected;

    public itemTaskbar(String linkIcon, String content) {
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(250, 45));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(30, 30));
        lblIcon.setIcon(new FlatSVGIcon("./icon/" + linkIcon));
        this.add(lblIcon);

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(170, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent.setForeground(FontColor);
        this.add(pnlContent);
    }

    public itemTaskbar(String linkIcon, String content1, String content2) {
        this.setLayout(new FlowLayout(0, 20, 50));
//        this.setPreferredSize(new Dimension(250, 45));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);

        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(110, 110));
        lblIcon.setIcon(new FlatSVGIcon("./icon/" + linkIcon));

        this.add(lblIcon);

        pnlContent = new JLabel(content1);
        pnlContent.setPreferredSize(new Dimension(170, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 200% $medium.font");
        pnlContent.setForeground(FontColor);
        this.add(pnlContent);

//        box[i].setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    public itemTaskbar(String linkImg, String tenSP, int soLuong) {

        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(380, 60));
        this.setBackground(Color.white);

        img = new InputImage1("");
        img.setUrl_img(linkImg);
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new FlowLayout(0,0, 0));
        right.setBorder(new EmptyBorder(10, 0, 0, 0));
        right.setOpaque(false);
        this.add(right,BorderLayout.CENTER);

        pnlContent = new JLabel(tenSP);
        pnlContent.putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
        pnlContent.setForeground(Color.black);
        right.add(pnlContent);

        pnlSoLuong = new JLabel("Số lượng: " + soLuong);
        pnlSoLuong.setPreferredSize(new Dimension(350,20));
        pnlSoLuong.putClientProperty("FlatLaf.style", "font: 100% $medium.font");
        pnlSoLuong.setForeground(Color.gray);
        right.add(pnlSoLuong);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(235, 237, 240));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(255, 255, 255));
        }
    }
}
