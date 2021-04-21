
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import project.dbConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import project.dbConnection;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harshit Goyal
 */
public class itemStock extends javax.swing.JFrame {
Connection con = null;
Statement st = null;
PreparedStatement ps = null;
ResultSet rs = null;
    
CardLayout cardLayout;
    /**
     * Creates new form itemStock
     */
    public itemStock() {
        initComponents();
        setIconImage();
        //fetch supplier and item code by calling function
        fetchSupplier();
        fetchItem();
        // calling date and time
        MyDate();
        MyTime();
        cardLayout = (CardLayout)(jPanel2.getLayout());
    }
    
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Stocker.png")));
    }
    
    // Date function
    public void MyDate(){
        SimpleDateFormat dFormate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        jLabel6.setText(dFormate.format(date));
    }
    
    // Time function
    public void MyTime(){
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dFormate = new SimpleDateFormat("hh:mm:ss a");
                Date date = new Date();
                jLabel5.setText(dFormate.format(date));
            }
        }).start();
    }
    
    // fecth supplier from table
    public void fetchSupplier(){
        try{
            con = dbConnection.getCon();
            String sql = "Select * from supplier";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                addSupplierComboBox.addItem(rs.getString("name"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane,"Coonection failed: \n"+ e.getMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }
    
    // fetch item code from table
    public void fetchItem(){
        try{
            con = dbConnection.getCon();
            String sql = "Select * from stock";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                addItemCodeComboBox.addItem(rs.getString("code"));
                updateItemCodeComboBox.addItem(rs.getString("code"));
                deleteItemCodeComboBox.addItem(rs.getString("code"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane,"Coonection failed: \n"+ e.getMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }
    
    // resert function for add items panel
    public void addNewItemReset(){
        addItemCodeText.setText("");
        addItemNameText.setText("");
        addSupplierComboBox.setSelectedIndex(0);
        addItemQtyText.setText("");
        addItemPurcahsePriceText.setText("");
        addItemSellPriceText.setText("");
        
    }
    
    public void addExistingItemReset(){
        addItemCodeComboBox.setSelectedIndex(0);
        existingItemNameText.setText("");
        existingItemSupplierText.setText("");
        existingItemQtyText.setText("");
    }
    
    // reset manage item panel
    public void updateReset(){
        updateItemCodeComboBox.setSelectedIndex(0);
        updateItemNameText.setText("");
        updateSupplierText.setText("");
        updateQtyText.setText("");
        updatePurchaseText.setText("");
        updateSellText.setText("");
    }
    public void deleteReset(){
        deleteItemCodeComboBox.setSelectedIndex(0);
        deleteNameText.setText("");
        deleteSupplierText.setText("");
        deleteQtyText.setText("");
        deletePurchaseText.setText("");
        deleteSellText.setText("");
    }
    
    // fetch all records of item in table from database
    public void allItemRecord(){
        try {
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from stock");
            //allEmpRecordTable.setAutoResizeMode(allEmpRecordTable.AUTO_RESIZE_OFF);
           // Set all the content to table
           DefaultTableModel table = (DefaultTableModel) allItemTable.getModel();
            table.setRowCount(0);
            while(rs.next()){
                Object record[] = {
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("supplier"),
                    rs.getString("quantity"),
                    rs.getString("purchase_price"),
                    rs.getString("sell_price")
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
        AddItem = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        addItemCodeComboBox = new javax.swing.JComboBox<>();
        existingItemNameText = new javax.swing.JTextField();
        existingItemSupplierText = new javax.swing.JTextField();
        existingItemQtyText = new javax.swing.JTextField();
        AddExistingItemAddBtn = new javax.swing.JButton();
        addExitingItemResetBtn = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        addItemCodeText = new javax.swing.JTextField();
        addItemNameText = new javax.swing.JTextField();
        addItemQtyText = new javax.swing.JTextField();
        addItemPurcahsePriceText = new javax.swing.JTextField();
        addItemSellPriceText = new javax.swing.JTextField();
        addSupplierComboBox = new javax.swing.JComboBox<>();
        addItemAddBtn = new javax.swing.JButton();
        addItemResetBtn = new javax.swing.JButton();
        ManageItem = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        updateItemCodeComboBox = new javax.swing.JComboBox<>();
        updateItemNameText = new javax.swing.JTextField();
        updateSupplierText = new javax.swing.JTextField();
        updateQtyText = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        updatePurchaseText = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        updateSellText = new javax.swing.JTextField();
        updateResetBtn = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        deleteNameText = new javax.swing.JTextField();
        deleteSupplierText = new javax.swing.JTextField();
        deleteQtyText = new javax.swing.JTextField();
        deleteItemCodeComboBox = new javax.swing.JComboBox<>();
        deleteBtn = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        deletePurchaseText = new javax.swing.JTextField();
        deleteSellText = new javax.swing.JTextField();
        deleteResetBtn = new javax.swing.JButton();
        AllStock = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allItemTable = new javax.swing.JTable();
        allStockItemNameText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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

        AddItem.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3), "Add Items", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Permanent Marker", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Add Existing Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Item Code:");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 68, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Item Name:");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 144, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Supplier:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 221, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Quantity:");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 298, -1, -1));

        addItemCodeComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addItemCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select item code" }));
        addItemCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemCodeComboBoxActionPerformed(evt);
            }
        });
        jPanel9.add(addItemCodeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 65, 199, -1));

        existingItemNameText.setEditable(false);
        existingItemNameText.setBackground(new java.awt.Color(255, 255, 255));
        existingItemNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(existingItemNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 141, 199, -1));

        existingItemSupplierText.setEditable(false);
        existingItemSupplierText.setBackground(new java.awt.Color(255, 255, 255));
        existingItemSupplierText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(existingItemSupplierText, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 218, 199, -1));

        existingItemQtyText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        existingItemQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                existingItemQtyTextKeyPressed(evt);
            }
        });
        jPanel9.add(existingItemQtyText, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 295, 199, -1));

        AddExistingItemAddBtn.setBackground(new java.awt.Color(102, 187, 0));
        AddExistingItemAddBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddExistingItemAddBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddExistingItemAddBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddBtn.png"))); // NOI18N
        AddExistingItemAddBtn.setText("Add");
        AddExistingItemAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddExistingItemAddBtnActionPerformed(evt);
            }
        });
        jPanel9.add(AddExistingItemAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 395, -1, -1));

        addExitingItemResetBtn.setBackground(new java.awt.Color(223, 77, 96));
        addExitingItemResetBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addExitingItemResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        addExitingItemResetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/clear.png"))); // NOI18N
        addExitingItemResetBtn.setText("Clear");
        addExitingItemResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExitingItemResetBtnActionPerformed(evt);
            }
        });
        jPanel9.add(addExitingItemResetBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 395, -1, -1));

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 55, 460, 480));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Add New Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Item Code:");
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 47, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Item Name:");
        jPanel10.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 105, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Supplier:");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 163, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Quantity:");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 221, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Prurchasing Price:");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 279, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Selling Price:");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 337, -1, -1));

        addItemCodeText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(addItemCodeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 44, 184, -1));

        addItemNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(addItemNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 102, 184, -1));

        addItemQtyText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addItemQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addItemQtyTextKeyPressed(evt);
            }
        });
        jPanel10.add(addItemQtyText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 218, 184, -1));

        addItemPurcahsePriceText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addItemPurcahsePriceText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addItemPurcahsePriceTextKeyPressed(evt);
            }
        });
        jPanel10.add(addItemPurcahsePriceText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 276, 184, -1));

        addItemSellPriceText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addItemSellPriceText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addItemSellPriceTextKeyPressed(evt);
            }
        });
        jPanel10.add(addItemSellPriceText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 334, 184, -1));

        addSupplierComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addSupplierComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier" }));
        jPanel10.add(addSupplierComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 160, 184, -1));

        addItemAddBtn.setBackground(new java.awt.Color(102, 187, 0));
        addItemAddBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addItemAddBtn.setForeground(new java.awt.Color(255, 255, 255));
        addItemAddBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddBtn.png"))); // NOI18N
        addItemAddBtn.setText("Add");
        addItemAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemAddBtnActionPerformed(evt);
            }
        });
        jPanel10.add(addItemAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 394, -1, -1));

        addItemResetBtn.setBackground(new java.awt.Color(223, 77, 96));
        addItemResetBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addItemResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        addItemResetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/clear.png"))); // NOI18N
        addItemResetBtn.setText("Clear");
        addItemResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemResetBtnActionPerformed(evt);
            }
        });
        jPanel10.add(addItemResetBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 394, -1, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 55, 480, 480));

        javax.swing.GroupLayout AddItemLayout = new javax.swing.GroupLayout(AddItem);
        AddItem.setLayout(AddItemLayout);
        AddItemLayout.setHorizontalGroup(
            AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AddItemLayout.setVerticalGroup(
            AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(AddItem, "AddItem");

        ManageItem.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3), "Manage Item", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Permanent Marker", 0, 36), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Udate Existing Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Item Code:");
        jPanel11.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 68, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Item Name:");
        jPanel11.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 129, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Supplier:");
        jPanel11.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 191, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Quantity:");
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 253, -1, -1));

        updateItemCodeComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateItemCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select item code" }));
        updateItemCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateItemCodeComboBoxActionPerformed(evt);
            }
        });
        jPanel11.add(updateItemCodeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 65, 199, -1));

        updateItemNameText.setEditable(false);
        updateItemNameText.setBackground(new java.awt.Color(255, 255, 255));
        updateItemNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(updateItemNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 126, 199, -1));

        updateSupplierText.setEditable(false);
        updateSupplierText.setBackground(new java.awt.Color(255, 255, 255));
        updateSupplierText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(updateSupplierText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 188, 199, -1));

        updateQtyText.setEditable(false);
        updateQtyText.setBackground(new java.awt.Color(255, 255, 255));
        updateQtyText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateQtyTextKeyPressed(evt);
            }
        });
        jPanel11.add(updateQtyText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 250, 199, -1));

        updateBtn.setBackground(new java.awt.Color(102, 187, 0));
        updateBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddBtn.png"))); // NOI18N
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel11.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 435, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("Prurchasing Price:");
        jPanel11.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 314, -1, -1));

        updatePurchaseText.setEditable(false);
        updatePurchaseText.setBackground(new java.awt.Color(255, 255, 255));
        updatePurchaseText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updatePurchaseText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updatePurchaseTextKeyPressed(evt);
            }
        });
        jPanel11.add(updatePurchaseText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 311, 199, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("Selling Price:");
        jPanel11.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 372, -1, -1));

        updateSellText.setEditable(false);
        updateSellText.setBackground(new java.awt.Color(255, 255, 255));
        updateSellText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateSellText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateSellTextKeyPressed(evt);
            }
        });
        jPanel11.add(updateSellText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 369, 199, -1));

        updateResetBtn.setBackground(new java.awt.Color(223, 77, 96));
        updateResetBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateResetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/clear.png"))); // NOI18N
        updateResetBtn.setText("Clear");
        updateResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateResetBtnActionPerformed(evt);
            }
        });
        jPanel11.add(updateResetBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 435, -1, -1));

        jPanel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 55, 460, 486));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Delete Existing Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Item Code:");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 68, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("Item Name:");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 129, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("Supplier:");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 190, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Quantity:");
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 251, -1, -1));

        deleteNameText.setEditable(false);
        deleteNameText.setBackground(new java.awt.Color(255, 255, 255));
        deleteNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel12.add(deleteNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 126, 184, -1));

        deleteSupplierText.setEditable(false);
        deleteSupplierText.setBackground(new java.awt.Color(255, 255, 255));
        deleteSupplierText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel12.add(deleteSupplierText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 187, 184, -1));

        deleteQtyText.setEditable(false);
        deleteQtyText.setBackground(new java.awt.Color(255, 255, 255));
        deleteQtyText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel12.add(deleteQtyText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 248, 184, -1));

        deleteItemCodeComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteItemCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select item code" }));
        deleteItemCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemCodeComboBoxActionPerformed(evt);
            }
        });
        jPanel12.add(deleteItemCodeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 65, 184, -1));

        deleteBtn.setBackground(new java.awt.Color(255, 54, 54));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Delete.png"))); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel12.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 435, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Purchasing Price:");
        jPanel12.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 312, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("Selling Price:");
        jPanel12.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 373, -1, -1));

        deletePurchaseText.setEditable(false);
        deletePurchaseText.setBackground(new java.awt.Color(255, 255, 255));
        deletePurchaseText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel12.add(deletePurchaseText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 309, 184, -1));

        deleteSellText.setEditable(false);
        deleteSellText.setBackground(new java.awt.Color(255, 255, 255));
        deleteSellText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel12.add(deleteSellText, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 370, 184, -1));

        deleteResetBtn.setBackground(new java.awt.Color(223, 77, 96));
        deleteResetBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteResetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/clear.png"))); // NOI18N
        deleteResetBtn.setText("Clear");
        deleteResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteResetBtnActionPerformed(evt);
            }
        });
        jPanel12.add(deleteResetBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 435, -1, -1));

        jPanel7.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 55, 470, 486));

        javax.swing.GroupLayout ManageItemLayout = new javax.swing.GroupLayout(ManageItem);
        ManageItem.setLayout(ManageItemLayout);
        ManageItemLayout.setHorizontalGroup(
            ManageItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ManageItemLayout.setVerticalGroup(
            ManageItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(ManageItem, "ManageItem");

        AllStock.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3), "All Stock", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Permanent Marker", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        allItemTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        allItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Name", "Supplier", "Quantity", "Purchasing Price", "Selling Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allItemTable.setRowHeight(26);
        allItemTable.setRowMargin(3);
        jScrollPane1.setViewportView(allItemTable);

        allStockItemNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        allStockItemNameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                allStockItemNameTextKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Item Code:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(allStockItemNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allStockItemNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout AllStockLayout = new javax.swing.GroupLayout(AllStock);
        AllStock.setLayout(AllStockLayout);
        AllStockLayout.setHorizontalGroup(
            AllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AllStockLayout.setVerticalGroup(
            AllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(AllStock, "AllStock");

        jPanel3.setBackground(new java.awt.Color(0, 124, 197));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel1.setFont(new java.awt.Font("Permanent Marker", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddItem.png"))); // NOI18N
        jLabel1.setText("Add Item");
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

        jPanel4.setBackground(new java.awt.Color(0, 124, 197));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel2.setFont(new java.awt.Font("Permanent Marker", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddItem.png"))); // NOI18N
        jLabel2.setText("Manage Item");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );

        jPanel5.setBackground(new java.awt.Color(0, 124, 197));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel3.setFont(new java.awt.Font("Permanent Marker", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AddItem.png"))); // NOI18N
        jLabel3.setText("All Stock");
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
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/01_Items_Stock.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
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
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
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

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // Back Button
        dispose();
        new MainPage().setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jPanel5.setBackground(new Color(0,124,197));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jPanel5.setBackground(new Color(0,91,147));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // All Stock Button
        cardLayout.show(jPanel2, "AllStock");
        
        // fest all the presented data from databaase
        allItemRecord();

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(0,124,197));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(0,91,147));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Manage Items Button
        cardLayout.show(jPanel2, "ManageItem");
        
        // reset all fields
        updateReset();
        deleteReset();

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jPanel3.setBackground(new Color(0,124,197));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jPanel3.setBackground(new Color(0,91,147));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // Add Item Button
        cardLayout.show(jPanel2, "AddItem");
        //reset
        addExistingItemReset();
        addNewItemReset();

    }//GEN-LAST:event_jLabel1MouseClicked

    private void addItemResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemResetBtnActionPerformed
        // Add New Item Reset Button
        addNewItemReset();
    }//GEN-LAST:event_addItemResetBtnActionPerformed

    private void addExitingItemResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addExitingItemResetBtnActionPerformed
        // Existing Item reset button
        addExistingItemReset();
    }//GEN-LAST:event_addExitingItemResetBtnActionPerformed

    private void addItemCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemCodeComboBoxActionPerformed
        // add exiting item - item code jcomobox
        if(addItemCodeComboBox.getSelectedItem().toString().equals("Select item code")){
            addExistingItemReset();
            
        }else{
            try{
            String code = addItemCodeComboBox.getSelectedItem().toString();
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("Select * from stock where code='"+code+"'");
            
            existingItemNameText.setText(rs.getString(2));
            existingItemSupplierText.setText(rs.getString(3));
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: \n" + e.getMessage());
        }finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException e) {
            }
            }
        }
    }//GEN-LAST:event_addItemCodeComboBoxActionPerformed

    private void addItemAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemAddBtnActionPerformed
        // Add Item Add Button
        String code = addItemCodeText.getText();
        String name = addItemNameText.getText();
        String supplier = addSupplierComboBox.getSelectedItem().toString();
        String qty = addItemQtyText.getText();
        String purchase = addItemPurcahsePriceText.getText();
        String sell = addItemSellPriceText.getText();
        try {
            con = dbConnection.getCon();
            ps = con.prepareStatement("insert into stock values(?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, supplier);
            ps.setString(4, qty);
            ps.setString(5, purchase);
            ps.setString(6, sell);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully saved");
            addNewItemReset();
            addItemCodeText.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: \n" +e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_addItemAddBtnActionPerformed

    private void AddExistingItemAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddExistingItemAddBtnActionPerformed
        // Add existing item Add Button
        String code = addItemCodeComboBox.getSelectedItem().toString();
        int newqty = Integer.parseInt(existingItemQtyText.getText());
        
        try{
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("select * from stock where code = '"+code+"'");
            int oldqty = Integer.parseInt(rs.getString(4));
            String qty = Integer.toString(newqty + oldqty);
            
            String sql = "UPDATE stock SET quantity=? where code = '"+code+"'";
            ps = con.prepareStatement(sql);
            ps.setString(1, qty);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Added");
            addExistingItemReset();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Connection failed" + e.getMessage());
        }finally{
            try {
                st.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_AddExistingItemAddBtnActionPerformed

    private void allStockItemNameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allStockItemNameTextKeyReleased
        // All Supplier Filter By ID
        if(allStockItemNameText.getText().equals("")){ 
            allItemRecord();
        }
        else
        try {
            String code = allStockItemNameText.getText();
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from stock where code ='"+code+"'");
            //allEmpRecordTable.setAutoResizeMode(allEmpRecordTable.AUTO_RESIZE_OFF); // to make  down scroll bar in table 
            // Set all the content to table
            DefaultTableModel table = (DefaultTableModel) allItemTable.getModel();
            table.setRowCount(0);
            while(rs.next()){
                Object record[] = {
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("supplier"),
                    rs.getString("quantity"),
                    rs.getString("purchase_price"),
                    rs.getString("sell_price")
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
    }//GEN-LAST:event_allStockItemNameTextKeyReleased

    private void updateItemCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateItemCodeComboBoxActionPerformed
        // Update Item code Combo Box
        if(updateItemCodeComboBox.getSelectedItem().toString().equals("Select item code")){
            updateReset();
            updateItemNameText.setEditable(false);
            updateSupplierText.setEditable(false);
            updateQtyText.setEditable(false);
            updatePurchaseText.setEditable(false);
            updateSellText.setEditable(false);
        }else{
            try{
            String code = updateItemCodeComboBox.getSelectedItem().toString();
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("Select * from stock where code='"+code+"'");

            updateItemNameText.setEditable(true);
            updateSupplierText.setEditable(true);
            updateQtyText.setEditable(true);
            updatePurchaseText.setEditable(true);
            updateSellText.setEditable(true);
            
            //.setText(rs.getString(1));
            updateItemNameText.setText(rs.getString(2));
            updateSupplierText.setText(rs.getString(3));
            updateQtyText.setText(rs.getString(4));
            updatePurchaseText.setText(rs.getString(5));
            updateSellText.setText(rs.getString(6));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: \n" + e.getMessage());
        }finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException e) {
            }
            }
        }
    }//GEN-LAST:event_updateItemCodeComboBoxActionPerformed

    private void deleteItemCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemCodeComboBoxActionPerformed
        // Delete pannel Stock item id combo box
        if(deleteItemCodeComboBox.getSelectedItem().toString().equals("Select item code")){
            deleteReset();
        }else{
            try{
            String code = deleteItemCodeComboBox.getSelectedItem().toString();
            con = dbConnection.getCon();
            st = con.createStatement();
            rs = st.executeQuery("Select * from stock where code='"+code+"'");
            deleteNameText.setText(rs.getString(2));
            deleteSupplierText.setText(rs.getString(3));
            deleteQtyText.setText(rs.getString(4));
            deletePurchaseText.setText(rs.getString(5));
            deleteSellText.setText(rs.getString(6));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: \n" + e.getMessage());
        }finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException e) {
            }
            }
        }
    }//GEN-LAST:event_deleteItemCodeComboBoxActionPerformed

    private void updateResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateResetBtnActionPerformed
        // Reset Button in Update Panel
        updateReset();
    }//GEN-LAST:event_updateResetBtnActionPerformed

    private void deleteResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteResetBtnActionPerformed
        // Reset button in delete Panel
        deleteReset();
        
    }//GEN-LAST:event_deleteResetBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // Update Button in manage Stock item Pannel
        String code = updateItemCodeComboBox.getSelectedItem().toString();
        String name = updateItemNameText.getText();
        String supplier = updateSupplierText.getText();
        String qty = updateQtyText.getText();
        String purchase = updatePurchaseText.getText();
        String sell = updateSellText.getText();
        try {
            con = dbConnection.getCon();
            ps = con.prepareStatement("update stock set name=?, supplier=?, quantity=?, purchase_price=?, sell_price=? where code='"+ code +"'");
            ps.setString(1, name);
            ps.setString(2, supplier);
            ps.setString(3, qty);
            ps.setString(4, purchase);
            ps.setString(5, sell);

            int response = JOptionPane.showConfirmDialog(null, "Do you want to sure to update?", "select", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully updated"); 
            }
            updateReset();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Connection failed: \n"+ e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
       // Delete Button in Manage Stock Item Panel
        String code = deleteItemCodeComboBox.getSelectedItem().toString();
        try {
            con = dbConnection.getCon();
            ps = con.prepareStatement("delete from stock where code='"+code+"'");
            int response = JOptionPane.showConfirmDialog(null, "Do you want to sure to delete?", "select", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete record sucessfylly");
                deleteReset();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: \n"+e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void existingItemQtyTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_existingItemQtyTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            existingItemQtyText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            existingItemQtyText.setEditable(true);
        } else {
            existingItemQtyText.setEditable(false);
            existingItemQtyText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_existingItemQtyTextKeyPressed

    private void addItemQtyTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addItemQtyTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            addItemQtyText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            addItemQtyText.setEditable(true);
        } else {
            addItemQtyText.setEditable(false);
            addItemQtyText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_addItemQtyTextKeyPressed

    private void addItemPurcahsePriceTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addItemPurcahsePriceTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            addItemPurcahsePriceText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            addItemPurcahsePriceText.setEditable(true);
        } else {
            addItemPurcahsePriceText.setEditable(false);
            addItemPurcahsePriceText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_addItemPurcahsePriceTextKeyPressed

    private void addItemSellPriceTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addItemSellPriceTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            addItemSellPriceText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            addItemSellPriceText.setEditable(true);
        } else {
            addItemSellPriceText.setEditable(false);
            addItemSellPriceText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_addItemSellPriceTextKeyPressed

    private void updateQtyTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateQtyTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            updateQtyText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            updateQtyText.setEditable(true);
        } else {
            updateQtyText.setEditable(false);
            updateQtyText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_updateQtyTextKeyPressed

    private void updatePurchaseTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updatePurchaseTextKeyPressed
        // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            updatePurchaseText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            updatePurchaseText.setEditable(true);
        } else {
            updatePurchaseText.setEditable(false);
            updatePurchaseText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_updatePurchaseTextKeyPressed

    private void updateSellTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateSellTextKeyPressed
         // Only enter numbers 
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            updateSellText.setEditable(true);
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            updateSellText.setEditable(true);
        } else {
            updateSellText.setEditable(false);
            updateSellText.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_updateSellTextKeyPressed

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
            java.util.logging.Logger.getLogger(itemStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(itemStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(itemStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(itemStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new itemStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddExistingItemAddBtn;
    private javax.swing.JPanel AddItem;
    private javax.swing.JPanel AllStock;
    private javax.swing.JPanel ManageItem;
    private javax.swing.JButton addExitingItemResetBtn;
    private javax.swing.JButton addItemAddBtn;
    private javax.swing.JComboBox<String> addItemCodeComboBox;
    private javax.swing.JTextField addItemCodeText;
    private javax.swing.JTextField addItemNameText;
    private javax.swing.JTextField addItemPurcahsePriceText;
    private javax.swing.JTextField addItemQtyText;
    private javax.swing.JButton addItemResetBtn;
    private javax.swing.JTextField addItemSellPriceText;
    private javax.swing.JComboBox<String> addSupplierComboBox;
    private javax.swing.JTable allItemTable;
    private javax.swing.JTextField allStockItemNameText;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JComboBox<String> deleteItemCodeComboBox;
    private javax.swing.JTextField deleteNameText;
    private javax.swing.JTextField deletePurchaseText;
    private javax.swing.JTextField deleteQtyText;
    private javax.swing.JButton deleteResetBtn;
    private javax.swing.JTextField deleteSellText;
    private javax.swing.JTextField deleteSupplierText;
    private javax.swing.JTextField existingItemNameText;
    private javax.swing.JTextField existingItemQtyText;
    private javax.swing.JTextField existingItemSupplierText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton updateBtn;
    private javax.swing.JComboBox<String> updateItemCodeComboBox;
    private javax.swing.JTextField updateItemNameText;
    private javax.swing.JTextField updatePurchaseText;
    private javax.swing.JTextField updateQtyText;
    private javax.swing.JButton updateResetBtn;
    private javax.swing.JTextField updateSellText;
    private javax.swing.JTextField updateSupplierText;
    // End of variables declaration//GEN-END:variables
}
