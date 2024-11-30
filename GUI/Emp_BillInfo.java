package GUI;

import src.Billing;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Emp_BillInfo extends JPanel{
    JFrame refreshFrame;

    private BufferedImage image;
    private JButton logoutButton;
    private JButton searchButton;
    private JButton addButton;
    private JButton refreshButton;
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();

    private JTextField textField1 = new JTextField("Search");
    private JTextField textField2 = new JTextField("Type ID");
    private JTextField textField3 = new JTextField("Enter Current Meter Reading");
    private JTextField textField4 = new JTextField("Enter Current Peak Meter");

    private JComboBox box1;

    private JButton updatePassword;
    private JButton viewExpiringCNIC;
    private JButton customerInfo;
    private JButton changeStatus;
    private JButton viewReport;
    private JButton viewBill;
    private JButton taxesInfo;

    private JScrollPane scroll;

    public Emp_BillInfo(String name)
    {
        setLayout(null);
        setNames(name);
        setImage();
        setButtons();
        setFields();
        setSideMenuButtons();
        setComboBox();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void setNames(String name)
    {
        name1.setText(name);
        name1.setFont(new Font("Inter",Font.BOLD,15));
        name1.setForeground(Color.white);
        name1.setBounds(12,189,250,18);
        name1.setVerticalAlignment(JLabel.CENTER);
        name1.setHorizontalAlignment(JLabel.CENTER);

        name2.setText(name + "!");
        name2.setFont(new Font("Inter",Font.BOLD,25));
        name2.setForeground(Color.BLACK);
        name2.setBounds(380,41,152,38);

        add(name1);
        add(name2);
    }

    private void setImage() {
        try {
            image = ImageIO.read(new File("Images/BillInfo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int imgWidth = getWidth();
            int imgHeight = getHeight();
            g2d.drawImage(image, 0, 0, imgWidth, imgHeight, this);
        }
    }

    private void setFields()
    {
        textField1.setFont(new Font("Inter",Font.BOLD,15));
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Search")) {
                    textField1.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText("Search");
                }
            }
        });
        textField1.setBorder(BorderFactory.createEmptyBorder());
        textField1.setBounds(623, 196,328,22);
        textField1.setForeground(new Color(173,173,173));

        textField2.setFont(new Font("Inter",Font.BOLD,15));
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Type ID")) {
                    textField2.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Type ID");
                }
            }
        });
        textField2.setBorder(BorderFactory.createEmptyBorder());
        textField2.setBounds(337, 132,138,22);
        textField2.setForeground(new Color(173,173,173));

        textField3.setFont(new Font("Inter",Font.BOLD,10));
        textField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField3.getText().equals("Enter Current Meter Reading")) {
                    textField3.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField3.getText().isEmpty()) {
                    textField3.setText("Enter Current Meter Reading");
                }
            }
        });
        textField3.setBorder(BorderFactory.createEmptyBorder());
        textField3.setBounds(516, 132,151,23);
        textField3.setForeground(new Color(173,173,173));

        textField4.setFont(new Font("Inter",Font.BOLD,10));
        textField4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField4.getText().equals("Enter Current Peak Meter")) {
                    textField4.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField4.getText().isEmpty()) {
                    textField4.setText("Enter Current Peak Meter");
                }
            }
        });
        textField4.setBorder(BorderFactory.createEmptyBorder());
        textField4.setBounds(699, 132,155,23);
        textField4.setForeground(new Color(173,173,173));

        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
    }

    private void setButtons()
    {
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        searchButton = new JButton();
        searchButton.setBounds(953, 200, 15, 15);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setOpaque(false);

        addButton = new JButton();
        addButton.setBounds(1251, 132, 65, 23);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(false);

        refreshButton = new JButton();
        refreshButton.setBounds(231, 116, 53, 21);
        refreshButton.setBorderPainted(false);
        refreshButton.setContentAreaFilled(false);
        refreshButton.setOpaque(false);

        add(logoutButton);
        add(searchButton);
        add(addButton);
    }

    private void setSideMenuButtons(){
        viewExpiringCNIC = new JButton();
        viewExpiringCNIC.setBounds(82, 610, 131, 15);
        viewExpiringCNIC.setBorderPainted(false);
        viewExpiringCNIC.setContentAreaFilled(false);
        viewExpiringCNIC.setOpaque(false);

        customerInfo = new JButton();
        customerInfo.setBounds(79, 325, 101, 20);
        customerInfo.setBorderPainted(false);
        customerInfo.setContentAreaFilled(false);
        customerInfo.setOpaque(false);

        updatePassword = new JButton();
        updatePassword.setBounds(79, 278, 117, 19);
        updatePassword.setBorderPainted(false);
        updatePassword.setContentAreaFilled(false);
        updatePassword.setOpaque(false);

        viewReport = new JButton();
        viewReport.setBounds(82, 562, 91, 15);
        viewReport.setBorderPainted(false);
        viewReport.setContentAreaFilled(false);
        viewReport.setOpaque(false);

        viewBill = new JButton();
        viewBill.setBounds(79, 516, 75, 14);
        viewBill.setBorderPainted(false);
        viewBill.setContentAreaFilled(false);
        viewBill.setOpaque(false);

        changeStatus = new JButton();
        changeStatus.setBounds(79, 420, 122, 18);
        changeStatus.setBorderPainted(false);
        changeStatus.setContentAreaFilled(false);
        changeStatus.setOpaque(false);

        taxesInfo = new JButton();
        taxesInfo.setBounds(79, 467, 83, 17);
        taxesInfo.setBorderPainted(false);
        taxesInfo.setContentAreaFilled(false);
        taxesInfo.setOpaque(false);


        add(viewExpiringCNIC);
        add(customerInfo);
        add(updatePassword);
        add(viewReport);
        add(viewBill);
        add(changeStatus);
        add(taxesInfo);
    }

    public void setValues(ArrayList<String> list, Billing obj_b)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);
        panel.setBorder(null);

        int value = 0;

        for(int i=0; i< list.size(); i++){
            String[] data = list.get(i).split(",");

            JLabel label = new JLabel();
            label.setLayout(null);
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(996,40));
            label.setMaximumSize(new Dimension(996, 40));
            label.setBorder(null);


            JLabel edit = new JLabel();
            edit.setText("<html><u>Edit</u></html>");
            edit.setFont(new Font("Inter",Font.BOLD,12));
            edit.setForeground(new Color(3,149,255));
            edit.setBounds((int) 949.5,2,46,14);
            edit.setVerticalAlignment(JLabel.CENTER);
            edit.setHorizontalAlignment(JLabel.CENTER);
            label.add(edit);

            JLabel delete = new JLabel();
            delete.setText("<html><u>Delete</u></html>");
            delete.setFont(new Font("Inter",Font.BOLD,12));
            delete.setForeground(new Color(3,149,255));
            delete.setBounds((int) 949.5,22,46,14);
            delete.setVerticalAlignment(JLabel.CENTER);
            delete.setHorizontalAlignment(JLabel.CENTER);
            label.add(delete);

            JTextField id = new JTextField();
            id.setText(data[0]);
            id.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            id.setForeground(new Color(93,93,93));
            id.setBounds((int) 7.5, 13, 46, 14);
            id.setHorizontalAlignment(JLabel.CENTER);
            id.setBorder(BorderFactory.createEmptyBorder());
            id.setEditable(false);
            id.setFocusable(false);

            JTextField month = new JTextField();
            month.setText(data[1]);
            month.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            month.setForeground(new Color(93,93,93));
            month.setBounds((int) 57.5, 13, 52, 14);
            month.setHorizontalAlignment(JLabel.CENTER);
            month.setBorder(BorderFactory.createEmptyBorder());
            month.setEditable(false);
            month.setFocusable(false);

            JTextField cmr = new JTextField();
            cmr.setText(data[2]);
            cmr.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            cmr.setForeground(new Color(93,93,93));
            cmr.setBounds((int) 109.5, 11, 90, 14);
            cmr.setHorizontalAlignment(JLabel.CENTER);
            cmr.setBorder(BorderFactory.createEmptyBorder());
            cmr.setEditable(false);
            cmr.setFocusable(false);

            String str_cmrp = data[3];
            if(str_cmrp.equals("not_supported")){
                str_cmrp = "";
            }
            JTextField cmrp = new JTextField();
            cmrp.setText(str_cmrp);
            cmrp.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            cmrp.setForeground(new Color(93,93,93));
            cmrp.setBounds((int) 200.5, 11, 86, 14);
            cmrp.setHorizontalAlignment(JLabel.CENTER);
            cmrp.setBorder(BorderFactory.createEmptyBorder());
            cmrp.setEditable(false);
            cmrp.setFocusable(false);

            JTextField eDate = new JTextField();
            eDate.setText(data[4]);
            eDate.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            eDate.setForeground(new Color(93,93,93));
            eDate.setBounds((int) 295.5, 13, 80, 14);
            eDate.setHorizontalAlignment(JLabel.CENTER);
            eDate.setBorder(BorderFactory.createEmptyBorder());
            eDate.setEditable(false);
            eDate.setFocusable(false);

            JTextField eCost = new JTextField();
            eCost.setText(data[5]);
            eCost.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            eCost.setForeground(new Color(93,93,93));
            eCost.setBounds((int) 386.5, 13, 80, 14);
            eCost.setHorizontalAlignment(JLabel.CENTER);
            eCost.setBorder(BorderFactory.createEmptyBorder());
            eCost.setEditable(false);
            eCost.setFocusable(false);

            JTextField salesTax = new JTextField();
            salesTax.setText(data[6]);
            salesTax.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            salesTax.setForeground(new Color(93,93,93));
            salesTax.setBounds((int) 466.5, 13, 80, 14);
            salesTax.setHorizontalAlignment(JLabel.CENTER);
            salesTax.setBorder(BorderFactory.createEmptyBorder());
            salesTax.setEditable(false);
            salesTax.setFocusable(false);

            JTextField fCharges = new JTextField();
            fCharges.setText(data[7]);
            fCharges.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            fCharges.setForeground(new Color(93,93,93));
            fCharges.setBounds((int) 546.5, 13, 80, 14);
            fCharges.setHorizontalAlignment(JLabel.CENTER);
            fCharges.setBorder(BorderFactory.createEmptyBorder());
            fCharges.setEditable(false);
            fCharges.setFocusable(false);

            JTextField total = new JTextField();
            total.setText(data[8]);
            total.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            total.setForeground(new Color(93,93,93));
            total.setBounds((int) 626.5, 13, 84, 14);
            total.setHorizontalAlignment(JLabel.CENTER);
            total.setBorder(BorderFactory.createEmptyBorder());
            total.setEditable(false);
            total.setFocusable(false);

            JTextField dueDate = new JTextField();
            dueDate.setText(data[9]);
            dueDate.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            dueDate.setForeground(new Color(93,93,93));
            dueDate.setBounds((int) 715.5, 13, 80, 14);
            dueDate.setHorizontalAlignment(JLabel.CENTER);
            dueDate.setBorder(BorderFactory.createEmptyBorder());
            dueDate.setEditable(false);
            dueDate.setFocusable(false);

            JTextField status = new JTextField();
            status.setText(data[10]);
            status.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            status.setForeground(new Color(93,93,93));
            status.setBounds((int) 808.5, 13, 52, 14);
            status.setHorizontalAlignment(JLabel.CENTER);
            status.setBorder(BorderFactory.createEmptyBorder());
            status.setEditable(false);
            status.setFocusable(false);

            JTextField pDate = new JTextField();
            pDate.setText(data[11]);
            pDate.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            pDate.setForeground(new Color(93,93,93));
            pDate.setBounds((int) 863.5, 13, 80, 14);
            pDate.setHorizontalAlignment(JLabel.CENTER);
            pDate.setBorder(BorderFactory.createEmptyBorder());
            pDate.setEditable(false);
            pDate.setFocusable(false);

            edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        if(!obj_b.isAccessAble(id.getText(),month.getText(),eDate.getText()))
                        {
                            JOptionPane.showMessageDialog(null,"Bill Not Editable","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            String currentText = edit.getText();
                            if (currentText.contains("Edit"))
                            {
                                edit.setText("<html><u>Update</u></html>");
                                cmr.setFocusable(true);
                                cmr.setEditable(true);
                                cmrp.setFocusable(true);
                                cmrp.setEditable(true);
                                eCost.setFocusable(true);
                                eCost.setEditable(true);
                                salesTax.setFocusable(true);
                                salesTax.setEditable(true);
                                fCharges.setFocusable(true);
                                fCharges.setEditable(true);
                                total.setFocusable(true);
                                total.setEditable(true);
                                dueDate.setFocusable(true);
                                dueDate.setEditable(true);
                                pDate.setFocusable(true);
                                pDate.setEditable(true);
                            }
                            else
                            {
                                cmr.setFocusable(false);
                                cmr.setEditable(false);
                                cmrp.setFocusable(false);
                                cmrp.setEditable(false);
                                eCost.setFocusable(false);
                                eCost.setEditable(false);
                                salesTax.setFocusable(false);
                                salesTax.setEditable(false);
                                fCharges.setFocusable(false);
                                fCharges.setEditable(false);
                                total.setFocusable(false);
                                total.setEditable(false);
                                dueDate.setFocusable(false);
                                dueDate.setEditable(false);
                                pDate.setFocusable(false);
                                pDate.setEditable(false);
                                edit.setText("<html><u>Edit</u></html>");

                                String line = id.getText() + "," + month.getText() + "," + cmr.getText() + "," + cmrp.getText() + "," + eDate.getText() + "," + eCost.getText() + "," + salesTax.getText() + "," + fCharges.getText() + "," + total.getText() + "," + dueDate.getText() + "," + status.getText() + "," + pDate.getText();
                                if (!obj_b.isValidEdit(line)) {
                                    showRefreshWindow("Images/Refreshequired2.png");
                                } else {
                                    obj_b.editBill(line);
                                    showRefreshWindow("Images/Refreshequired.png");
                                }
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    edit.setForeground(new Color(210,26,26));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    edit.setForeground(new Color(3,149,255));
                }
            });

            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        if(!obj_b.isAccessAble(id.getText(),month.getText(),eDate.getText())){
                            JOptionPane.showMessageDialog(null,"Bill Not Deleteable","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            int result = JOptionPane.showConfirmDialog(null, "Delete ID: " + id.getText() + "\nDo you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                try {
                                    obj_b.deleteBill(id.getText(), month.getText(), eDate.getText());
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                showRefreshWindow("Images/Refreshequired.png");
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    delete.setForeground(new Color(210,26,26));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    delete.setForeground(new Color(3,149,255));
                }
            });

            if(value==0){
                label.setBackground(new Color(255,255,255));
                id.setBackground(new Color(255,255,255));
                month.setBackground(new Color(255,255,255));
                cmr.setBackground(new Color(255,255,255));
                cmrp.setBackground(new Color(255,255,255));
                eDate.setBackground(new Color(255,255,255));
                eCost.setBackground(new Color(255,255,255));
                salesTax.setBackground(new Color(255,255,255));
                fCharges.setBackground(new Color(255,255,255));
                total.setBackground(new Color(255,255,255));
                dueDate.setBackground(new Color(255,255,255));
                status.setBackground(new Color(255,255,255));
                pDate.setBackground(new Color(255,255,255));
                value=1;
            }
            else {
                value=0;
                label.setBackground(new Color(217,217,217));
                id.setBackground(new Color(217,217,217));
                month.setBackground(new Color(217,217,217));
                cmr.setBackground(new Color(217,217,217));
                cmrp.setBackground(new Color(217,217,217));
                eDate.setBackground(new Color(217,217,217));
                eCost.setBackground(new Color(217,217,217));
                salesTax.setBackground(new Color(217,217,217));
                fCharges.setBackground(new Color(217,217,217));
                total.setBackground(new Color(217,217,217));
                dueDate.setBackground(new Color(217,217,217));
                status.setBackground(new Color(217,217,217));
                pDate.setBackground(new Color(217,217,217));
            }

            label.add(id);
            label.add(month);
            label.add(cmr);
            label.add(cmrp);
            label.add(eDate);
            label.add(eCost);
            label.add(salesTax);
            label.add(fCharges);
            label.add(total);
            label.add(dueDate);
            label.add(status);
            label.add(pDate);

            panel.add(label);
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 431));
        scroll.setBounds((int) 315.5, 299, 1000, 431);
        scroll.setBorder(null);

        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, Billing obj_b) {
        if (scroll != null) {
            remove(scroll);
        }
        setValues(newList,obj_b);
        textField1.setText("Search");
        textField2.setText("Type ID");
        textField3.setText("Enter Current Meter Reading");
        textField4.setText("Enter Current Peak Meter");
        revalidate();
        repaint();
    }

    private void setComboBox() {
        String[] options1 = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov","Dec" };

        box1 = new JComboBox<>(options1);
        box1.setBounds(876, 130, 121, 27);
        box1.setFont(new Font("Inter", Font.BOLD, 12));
        box1.setForeground(new Color(173, 173, 173));
        box1.setBackground(Color.white);
        box1.setBorder(new EmptyBorder(0, 0, 0, 0));
        box1.setMaximumRowCount(12);

        add(box1);
    }

    public void showRefreshWindow(String link) {
        refreshFrame = new JFrame();
        refreshFrame.setSize(300, 150);
        refreshFrame.setLocationRelativeTo(this);
        refreshFrame.setResizable(false);
        refreshFrame.setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            private Image image;
            {
                try {
                    image = ImageIO.read(new File(link));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    Graphics2D g2d = (Graphics2D) g;

                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    int imgWidth = getWidth();
                    int imgHeight = getHeight();
                    g2d.drawImage(image, 0, 0, imgWidth, imgHeight, this);
                }
            }
        };

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(300, 150));
        panel.setBounds(0, 0, 300, 150);

        mainPanel.add(panel, BorderLayout.CENTER);
        panel.add(refreshButton);
        refreshFrame.add(mainPanel);
        refreshFrame.setVisible(true);
    }

    public String getSearched(){
        return textField1.getText();
    }
    public String getID(){
        return textField2.getText();
    }
    public String getCMR(){
        return textField3.getText();
    }
    public String getCMRP(){
        return textField4.getText();
    }
    public String getMonth(){
        return (String) box1.getSelectedItem();
    }

    public JButton getSearchButton(){
        return searchButton;
    }
    public JButton getAddButton(){
        return addButton;
    }
    public JButton getLogoutButton() {
        return logoutButton;
    }
    public JButton getRefreshButton() {
        return refreshButton;
    }
    public JFrame getRefreshFrame(){return refreshFrame;}

    public JButton getViewExpireButton(){return viewExpiringCNIC;}
    public JButton getCustomerInfoButton(){return customerInfo;}
    public JButton getViewReportButton(){return viewReport;}
    public JButton getUpdatePasswordButton(){return updatePassword;}
    public JButton getViewBillButton(){return viewBill;}
    public JButton getChangeStatusButton(){return changeStatus;}
    public JButton gettaxesButton(){return taxesInfo;}

    public JPanel getPanel() {
        return this;
    }
}
