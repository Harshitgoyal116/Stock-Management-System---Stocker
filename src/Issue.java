/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import project.dbConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import project.dbConnection;

/**
 *
 * @author Harshit Goyal
 */
public class Issue extends javax.swing.JFrame {

    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    CardLayout cardLayout;

    /**
     * Creates new form Issue
     */
    public Issue() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Stocker.png")));

        // fetch item code and employee
        fetchItem();
        fetchEmp();
        // My date and Time
        MyDate();
        MyTime();
        cardLayout = (CardLayout) (jPanel2.getLayout());
    }

    // Date function
    public void MyDate() {
        SimpleDateFormat dFormate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        jLabel6.setText(dFormate.format(date));
    }

    // Time function
    public void MyTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dFormate = new SimpleDateFormat("hh:mm:ss a");
                Date date = new Date();
                jLabel5.setText(dFormate.format(date));
            }
        }).start();
    }

    // fetch item
    public void fetchItem() {
        try {
            con = dbConnection.getCon();
            String sql = "Select * from stock";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                issueItemCodeComboBox.addItem(rs.getString("code"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Coonection failed: \n" + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    public void fetchEmp() {
        try {
            con = dbConnection.getCon();
            String sql = "Select * from employee";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                issueEmpIdComboBox.addItem(rs.getString("id"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Coonection failed: \n" + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    public void issueReset() {
        issueItemCodeComboBox.setSelectedIndex(0);
        issueItemNameText.setText("");
        issueItemAvailableText.setText("");
        issueProductCostText.setText("");
    }

    public void Reset() {
        issueItemCodeComboBox.setSelectedIndex(0);
        issueItemNameText.setText("");
        issueItemAvailableText.setText("");
        issueProductCostText.setText("");
        issueEmpIdComboBox.setSelectedIndex(0);
        issueByText.setText("");
        issueQtyText.setText("");
        issueTotalCostText.setText("");
    }
    
    // fetch all records of item in table from database
    public void allIssueRecord(){
        try {
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from issue");
            //allEmpRecordTable.setAutoResizeMode(allEmpRecordTable.AUTO_RESIZE_OFF);
           // Set all the content to table
           DefaultTableModel table = (DefaultTableModel) allIssueTable.getModel();
            table.setRowCount(0);
            while(rs.next()){
                Object record[] = {
                    rs.getString("item_code"),
                    rs.getString("item_name"),
                    rs.getString("emp_id"),
                    rs.getString("issue_by"),
                    rs.getString("quantity"),
                    rs.getString("item_available"),
                    rs.getString("total_cost")
                };
                table.addRow(record);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Connection failed: \n"+ e.getMessage());
        }finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }
    
    // save all issue table record in csv file and text file
     public void save1(){
        String filePath = "IssueByAllRecords.txt";
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0; i < allIssueTable.getRowCount(); i++){//rows
                for(int j = 0; j < allIssueTable.getColumnCount(); j++){//columns
                    bw.write(allIssueTable.getValueAt(i, j).toString()+" || ");
                     
                }
                
                bw.newLine();
                
            }
           //JOptionPane.showMessageDialog(null, "You file is successfully saved");
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save2(){
        String filePath = "D://Issue_By_All_Record.csv";
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < allIssueTable.getRowCount(); i++){//rows
                for(int j = 0; j < allIssueTable.getColumnCount(); j++){//columns
                    bw.write(allIssueTable.getValueAt(i, j).toString()+", ");
                }
                bw.newLine();
            }
           //JOptionPane.showMessageDialog(null, "You file is successfully saved");
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        IssueItem = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        issueItemNameText = new javax.swing.JTextField();
        issueByText = new javax.swing.JTextField();
        issueQtyText = new javax.swing.JTextField();
        issueBtn = new javax.swing.JButton();
        IssueResetBtn = new javax.swing.JButton();
        issueItemCodeComboBox = new javax.swing.JComboBox<>();
        issueEmpIdComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        issueItemAvailableText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        issueTotalCostText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        issueProductCostText = new javax.swing.JTextField();
        AllIssueItem = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allIssueTable = new javax.swing.JTable();
        allIssueByText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        allIssueSaveBtn = new javax.swing.JButton();
        allIssueDeleteBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stocker");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 139, 208));

        jPanel2.setLayout(new java.awt.CardLayout());

        IssueItem.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3), "Issue Item", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Permanent Marker", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Add New Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 102, 204))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Item Code:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Item Name:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Employee ID:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Issue By:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Quantity:");

        issueItemNameText.setEditable(false);
        issueItemNameText.setBackground(new java.awt.Color(255, 255, 255));
        issueItemNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        issueByText.setEditable(false);
        issueByText.setBackground(new java.awt.Color(255, 255, 255));
        issueByText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        issueQtyText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issueQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                issueQtyTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                issueQtyTextKeyReleased(evt);
            }
        });

        issueBtn.setBackground(new java.awt.Color(102, 187, 0));
        issueBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        issueBtn.setForeground(new java.awt.Color(255, 255, 255));
        issueBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/issueBtn.png"))); // NOI18N
        issueBtn.setText("Issue");
        issueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueBtnActionPerformed(evt);
            }
        });

        IssueResetBtn.setBackground(new java.awt.Color(223, 77, 96));
        IssueResetBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        IssueResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        IssueResetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/clear.png"))); // NOI18N
        IssueResetBtn.setText("Clear");
        IssueResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueResetBtnActionPerformed(evt);
            }
        });

        issueItemCodeComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issueItemCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select item code" }));
        issueItemCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueItemCodeComboBoxActionPerformed(evt);
            }
        });

        issueEmpIdComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issueEmpIdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select ID" }));
        issueEmpIdComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueEmpIdComboBoxActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Item Available");

        issueItemAvailableText.setEditable(false);
        issueItemAvailableText.setBackground(new java.awt.Color(255, 255, 255));
        issueItemAvailableText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Total Cost");

        issueTotalCostText.setEditable(false);
        issueTotalCostText.setBackground(new java.awt.Color(255, 255, 255));
        issueTotalCostText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Product Cost");

        issueProductCostText.setEditable(false);
        issueProductCostText.setBackground(new java.awt.Color(255, 255, 255));
        issueProductCostText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(IssueResetBtn)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(issueQtyText)
                            .addComponent(issueByText)
                            .addComponent(issueItemNameText)
                            .addComponent(issueEmpIdComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(issueItemCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(issueItemAvailableText)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(issueBtn)
                    .addComponent(jLabel2)
                    .addComponent(issueProductCostText)
                    .addComponent(jLabel11)
                    .addComponent(issueTotalCostText))
                .addGap(119, 119, 119))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(issueItemCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(issueItemAvailableText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(issueItemNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(issueProductCostText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(issueEmpIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(issueTotalCostText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(issueByText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(issueQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(issueBtn))
                .addGap(59, 59, 59)
                .addComponent(IssueResetBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout IssueItemLayout = new javax.swing.GroupLayout(IssueItem);
        IssueItem.setLayout(IssueItemLayout);
        IssueItemLayout.setHorizontalGroup(
            IssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IssueItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        IssueItemLayout.setVerticalGroup(
            IssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IssueItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(IssueItem, "IssueItem");

        AllIssueItem.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3), "All Issue Items", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Permanent Marker", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        allIssueTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        allIssueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Emp ID", "Issue By", "Quantity", "Available Item", "Total Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allIssueTable.setRowHeight(26);
        allIssueTable.setRowMargin(3);
        jScrollPane1.setViewportView(allIssueTable);

        allIssueByText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        allIssueByText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                allIssueByTextKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Issue By:");

        allIssueSaveBtn.setBackground(new java.awt.Color(102, 187, 0));
        allIssueSaveBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        allIssueSaveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/save.png"))); // NOI18N
        allIssueSaveBtn.setText("Save all records");
        allIssueSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allIssueSaveBtnActionPerformed(evt);
            }
        });

        allIssueDeleteBtn.setBackground(new java.awt.Color(255, 54, 54));
        allIssueDeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        allIssueDeleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Delete.png"))); // NOI18N
        allIssueDeleteBtn.setText("Delete all records");
        allIssueDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allIssueDeleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(allIssueByText, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(allIssueDeleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(allIssueSaveBtn)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allIssueByText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allIssueSaveBtn)
                    .addComponent(allIssueDeleteBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout AllIssueItemLayout = new javax.swing.GroupLayout(AllIssueItem);
        AllIssueItem.setLayout(AllIssueItemLayout);
        AllIssueItemLayout.setHorizontalGroup(
            AllIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllIssueItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AllIssueItemLayout.setVerticalGroup(
            AllIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllIssueItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(AllIssueItem, "AllIssueItem");

        jPanel3.setBackground(new java.awt.Color(0, 124, 197));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel1.setFont(new java.awt.Font("Permanent Marker", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddItem.png"))); // NOI18N
        jLabel1.setText("Issue Item");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jPanel5.setBackground(new java.awt.Color(0, 124, 197));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel3.setFont(new java.awt.Font("Permanent Marker", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddItem.png"))); // NOI18N
        jLabel3.setText("All Issue Items");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stocker");

        jLabel5.setFont(new java.awt.Font("Engravers MT", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Time");

        jLabel6.setFont(new java.awt.Font("Engravers MT", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/back_1.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/02_Issue_Items.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(308, 308, 308)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(57, 57, 57))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // Add Item Button
        cardLayout.show(jPanel2, "IssueItem");
        Reset();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jPanel3.setBackground(new Color(0, 91, 147));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jPanel3.setBackground(new Color(0, 124, 197));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // All Stock Button
        cardLayout.show(jPanel2, "AllIssueItem");
        
        allIssueRecord();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jPanel5.setBackground(new Color(0, 91, 147));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jPanel5.setBackground(new Color(0, 124, 197));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // Back Button
        dispose();
        new MainPage().setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void issueItemCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueItemCodeComboBoxActionPerformed
        // Issue Item - item code jcomobox
        if (issueItemCodeComboBox.getSelectedItem().toString().equals("Select item code")) {
            issueReset();

        } else {
            try {
                String code = issueItemCodeComboBox.getSelectedItem().toString();
                con = dbConnection.getCon();
                st = con.createStatement();
                rs = st.executeQuery("Select * from stock where code='" + code + "'");

                issueItemNameText.setText(rs.getString(2));//name
                issueItemAvailableText.setText(rs.getString(4));//Quantity
                issueProductCostText.setText(rs.getString(6));//sell_price

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Connection failed: \n" + e.getMessage());
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }//GEN-LAST:event_issueItemCodeComboBoxActionPerformed

    private void issueEmpIdComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueEmpIdComboBoxActionPerformed
        // Select Emp id to issue item
        if (issueEmpIdComboBox.getSelectedItem().toString().equals("Select ID")) {
            issueByText.setText("");

        } else {
            try {
                String id = issueEmpIdComboBox.getSelectedItem().toString();
                con = dbConnection.getCon();
                st = con.createStatement();
                rs = st.executeQuery("Select * from employee where id='" + id + "'");

                issueByText.setText(rs.getString(2));//name

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Connection failed: \n" + e.getMessage());
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    //JOptionPane.showMessageDialog(null, "Connection failed: \n" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_issueEmpIdComboBoxActionPerformed

    private void IssueResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueResetBtnActionPerformed
        // Reset Button
        Reset();
    }//GEN-LAST:event_IssueResetBtnActionPerformed

    private void issueQtyTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_issueQtyTextKeyReleased
        // TODO add your handling code here:
        if (issueQtyText.getText().equals("")) {
            issueTotalCostText.setText("");
        } else {
            int price = Integer.parseInt(issueProductCostText.getText());
            int qty = Integer.parseInt(issueQtyText.getText());

            String Total = Integer.toString(qty * price);
            issueTotalCostText.setText(Total);
        }
    }//GEN-LAST:event_issueQtyTextKeyReleased

    private void issueQtyTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_issueQtyTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            issueQtyText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            issueQtyText.setEditable(true);
        } else {
            issueQtyText.setEditable(false);
            issueQtyText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_issueQtyTextKeyPressed

    private void issueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueBtnActionPerformed
        // Issue By - Save in Table
        String code = issueItemCodeComboBox.getSelectedItem().toString();
        String name = issueItemNameText.getText();
        String empId = issueEmpIdComboBox.getSelectedItem().toString();
        String issueBy = issueByText.getText();
        String newqty = issueQtyText.getText();
        String itemAvailable = issueItemAvailableText.getText();
        String totalCost = issueTotalCostText.getText();
        try {
            con = dbConnection.getCon();
            // insert into table
            ps = con.prepareStatement("insert into issue values(?,?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, empId);
            ps.setString(4, issueBy);
            ps.setString(5, newqty);
            ps.setString(6, itemAvailable);
            ps.setString(7, totalCost);
            ps.executeUpdate();
            // remove quantity which was issue and store in issue table
            st = con.createStatement();
            rs = st.executeQuery("select * from stock where code = '"+code+"'");
            int oldQty = Integer.parseInt(rs.getString(4));
            int newQty = Integer.parseInt(issueQtyText.getText());
            if(newQty > oldQty){
                JOptionPane.showMessageDialog(null, "Insufficient stock..!");
                issueQtyText.setText("");
                issueQtyText.grabFocus();
                return; 
            }
            String qty = Integer.toString(oldQty - newQty);
            
            String sql = "UPDATE stock SET quantity=? where code = '"+code+"'";
            ps = con.prepareStatement(sql);
            ps.setString(1, qty);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Sucessfully saved");
            Reset();
            //addItemCodeText.requestFocus();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: \n" +e.getMessage());
        }finally{
            try {
                st.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_issueBtnActionPerformed

    private void allIssueByTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allIssueByTextKeyReleased
        // All Issues Filter By Employee Name
        if(allIssueByText.getText().equals("")){ 
            allIssueRecord();
        }
        else
        try {
            String issue = allIssueByText.getText();
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from issue where issue_by ='"+issue+"'");
            //allEmpRecordTable.setAutoResizeMode(allEmpRecordTable.AUTO_RESIZE_OFF); // to make  down scroll bar in table 
            // Set all the content to table
            DefaultTableModel table = (DefaultTableModel) allIssueTable.getModel();
            table.setRowCount(0);
            while(rs.next()){
                Object record[] = {
                    rs.getString("item_code"),
                    rs.getString("item_name"),
                    rs.getString("emp_id"),
                    rs.getString("issue_by"),
                    rs.getString("quantity"),
                    rs.getString("item_available"),
                    rs.getString("total_cost")
                };
                table.addRow(record);
            }
        } catch (SQLException e) {
           
            JOptionPane.showMessageDialog(null, "Connection failed: \n"+e.getMessage());
        }finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_allIssueByTextKeyReleased

    private void allIssueDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allIssueDeleteBtnActionPerformed
        // Delete Button (Delete All issue record)
        try {
            con = dbConnection.getCon();
            ps = con.prepareStatement("delete from issue");
            int response = JOptionPane.showConfirmDialog(rootPane, "Dow you want to sure?", "Selected", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully deleted");
            setVisible(false);
            new Issue().setVisible(true);
            }
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Connection failed: \n" + e.getMessage());
        }finally{
            try {
                st.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_allIssueDeleteBtnActionPerformed

    private void allIssueSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allIssueSaveBtnActionPerformed
        // Save Button
        save1();
        save2();
        JOptionPane.showMessageDialog(null, "You file is successfully saved");
    }//GEN-LAST:event_allIssueSaveBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Issue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AllIssueItem;
    private javax.swing.JPanel IssueItem;
    private javax.swing.JButton IssueResetBtn;
    private javax.swing.JTextField allIssueByText;
    private javax.swing.JButton allIssueDeleteBtn;
    private javax.swing.JButton allIssueSaveBtn;
    private javax.swing.JTable allIssueTable;
    private javax.swing.JButton issueBtn;
    private javax.swing.JTextField issueByText;
    private javax.swing.JComboBox<String> issueEmpIdComboBox;
    private javax.swing.JTextField issueItemAvailableText;
    private javax.swing.JComboBox<String> issueItemCodeComboBox;
    private javax.swing.JTextField issueItemNameText;
    private javax.swing.JTextField issueProductCostText;
    private javax.swing.JTextField issueQtyText;
    private javax.swing.JTextField issueTotalCostText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
