/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ehis;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Joe
 */
public class CalendarDoctor extends javax.swing.JPanel {
    
    String docID;
    //EHIS ehis;
    //DoctorNursePanel panel;
    


    /**
     * Creates new form CalendarDoctor
     */
    public CalendarDoctor(String dID) {
        //ehis = EHIS.getEhis();
        initComponents();
        docID = dID;

        //calendar listener
        cal_Chooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    String date = TimeAndDate.dateToDateString(cal_Chooser.getDate());
                    
                    String where = "WHERE DoctorID = '" + docID + "' AND Date = '" + date + "'";
                    //get existing record and/or delete
                    
                    Statement stat = EHIS.getConnection().createStatement();
                    String sql = "SELECT * FROM calendar " + where;
                    ResultSet rs = stat.executeQuery(sql);
                    lab_Date.setText(date);
                    if(rs.next()){
                        String endTime = rs.getString("EndTime");
                        String startTime = rs.getString("StartTime");

                        lbl_StartTime.setText(docID);
                        lbl_EndTime.setText("End: " + endTime);
                        lbl_StartTime.setText("Start: " + startTime);
                        
                    } else{
                        lbl_EndTime.setText("End: ");
                        lbl_StartTime.setText("Start: ");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CalendarDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //set to todays date
        cal_Chooser.setDate(new Date());

        //appointment list listener
        tbl_appointments.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //TODO-Add something
            }
        });
        
    }
    
    
    

    public void refresh() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_appointments = new javax.swing.JTable();
        cal_Chooser = new com.toedter.calendar.JCalendar();
        lab_Date = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_StartTime = new javax.swing.JLabel();
        btn_EndTimeEdit = new javax.swing.JButton();
        lbl_EndTime = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        tbl_appointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Time", "Patient", "AppointType"
            }
        ));
        jScrollPane1.setViewportView(tbl_appointments);

        lab_Date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lab_Date.setText("Date:  Feb 7, 2013");

        jLabel2.setText("My Hours:");

        lbl_StartTime.setText("Start:");

        btn_EndTimeEdit.setText("Edit");
        btn_EndTimeEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EndTimeEditActionPerformed(evt);
            }
        });

        lbl_EndTime.setText("End:");

        backButton.setText("<-back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_EndTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(btn_EndTimeEdit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbl_StartTime))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(lab_Date))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton)
                        .addGap(62, 62, 62)
                        .addComponent(cal_Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cal_Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_Date)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)
                        .addComponent(lbl_StartTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_EndTimeEdit)
                            .addComponent(lbl_EndTime))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_EndTimeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EndTimeEditActionPerformed
        JDialog dialog = new JDialog(EHIS.getEhis(), "Edit Hours", true);
        DoctorHoursPanel panel = new DoctorHoursPanel(dialog, TimeAndDate.dateToDateString(cal_Chooser.getDate()), docID);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setVisible(true);
        refresh();
        
    }//GEN-LAST:event_btn_EndTimeEditActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
         DoctorNursePanel panel = new DoctorNursePanel(docID);
         EHIS ehis = EHIS.getEhis();
         ehis.setContentPane(panel);
         ehis.pack();
         ehis.validate();
    }//GEN-LAST:event_backButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton btn_EndTimeEdit;
    private com.toedter.calendar.JCalendar cal_Chooser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_Date;
    private javax.swing.JLabel lbl_EndTime;
    private javax.swing.JLabel lbl_StartTime;
    private javax.swing.JTable tbl_appointments;
    // End of variables declaration//GEN-END:variables
}
