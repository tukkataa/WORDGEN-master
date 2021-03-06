/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.songkhla.wordgen;

import static com.songkhla.wordgen.ChargeOverView.jTable1;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 *
 * @author Computer
 */
public class SuspectBookOverview extends javax.swing.JDialog {

    /**
     * Creates new form CrimeBookOverview
     */
    public SuspectBookOverview() {
        initComponents();
           ImageIcon img = new ImageIcon("D://Master//WD.png");
            setIconImage(img.getImage());
            setTitle("ระบบสำนวนอิเล็กทรอนิกส์ (CRIMES E-inquiry) BETA");
            pack();
            setLocationRelativeTo(null);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
        RefreshData();
        
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(4, 93, 179));

        jLabel1.setFont(new java.awt.Font("TH SarabunPSK", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("สมุดคุมคดีผู้ต้องหา");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addContainerGap(1023, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("TH SarabunPSK", 0, 22)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setFont(new Font("TH SarabunPSK", Font.BOLD, 20));
        jTable1.getTableHeader().setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("TH SarabunPSK", 0, 22)); // NOI18N
        jButton1.setText("สร้างเอกสารสมุดคุมคดีผู้ต้องหา");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(0, 54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        WriteExcelBookSuspect.crimebook();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SuspectBookOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuspectBookOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuspectBookOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuspectBookOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuspectBookOverview().setVisible(true);
            }
        });
    }
 public void RefreshData(){
        try{
            
        Connection con = ConnectDatabase.connect();
        Statement stmt = con.createStatement();
        String sql = "Select Person.*,crimecase.*,chargecase.ChargeCodeCase ChargeCase,chargecase.ChargeNameCase ChargeNameCase  from person\n"
                     + "left join crimecase on crimecase.caseid=person.caseidperson\n"
                     + "left join chargecase on crimecase.CaseId=chargecase.ChargeCaseId\n"
                     + "where person.typePerson='ผู้ต้องหา'";

//                + "left join Person on Person.caseIdPerson = CrimeCase.CaseId "+getFi lterCondition();
    System.out.println("Sqll : "+sql);
        ResultSet rs = stmt.executeQuery(sql);
            
        Vector<Vector> tabledata = new Vector<Vector>();
        while(rs.next()){
            Vector<String> row = new Vector<String>();
            row.add(rs.getString("NoPerson"));
            row.add(rs.getString("PeopleRegistrationID"));            
            row.add(rs.getString("FullNamePerson"));
            row.add(rs.getString("crimecasenoyear"));     
            row.add(rs.getString("CourtSuspect"));           
            row.add(rs.getString("ChargeNameCase"));
            row.add(rs.getString("StatusSuspect"));
            row.add(rs.getString("StatusBail"));
            row.add(rs.getString("BailDate"));
            row.add(rs.getString("ArrestDateTime"));
            row.add(rs.getString("Investigator_Result"));
            row.add(rs.getString("Prosecutor_Result"));
            row.add(rs.getString("CourtResult"));
          
           
//            row.add(rs.getString("Investigator_Result"));
            tabledata.add(row);
        }
        rs.close();
        stmt.close();
        Vector ColumnName = new Vector();
        ColumnName.add("ลำดับ");
        ColumnName.add("เลขบัตรประจำตัวประชาชน"); 
        ColumnName.add("ชื่อผู้ต้องหา"); 
        ColumnName.add("เลขคดี/ปี");
        ColumnName.add("ศาล"); 
        ColumnName.add("ข้อหา");
        ColumnName.add("สถานะ");
        ColumnName.add("สถานะประกัน"); 
        ColumnName.add("วันที่ประกัน");
        ColumnName.add("วันที่ถูกจับกุม");    
        ColumnName.add("ผลการสอบสวน");
        ColumnName.add("ผลคดีชั้นอัยการ");
        ColumnName.add("ผลคดีชั้นศาล");
  

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tabledata,
            ColumnName
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);                
        jTable1.getColumnModel().getColumn(1).setMinWidth(50);
        jTable1.getColumnModel().getColumn(2).setMinWidth(125);
        jTable1.getColumnModel().getColumn(3).setMinWidth(125);
        jTable1.getColumnModel().getColumn(4).setMinWidth(95);
         jTable1.getColumnModel().getColumn(5).setMinWidth(180);
        jTable1.getColumnModel().getColumn(6).setMinWidth(180);
        jTable1.getColumnModel().getColumn(7).setMinWidth(180);
        jTable1.getColumnModel().getColumn(8).setMinWidth(180); 
        jTable1.getColumnModel().getColumn(9).setMinWidth(180);
        jTable1.getColumnModel().getColumn(10).setMinWidth(140);
         jTable1.getColumnModel().getColumn(11).setMinWidth(140);
          jTable1.getColumnModel().getColumn(12).setMinWidth(140);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
