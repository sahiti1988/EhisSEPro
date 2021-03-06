/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ehis;

import java.awt.Dialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Joe
 */
public class DoctorHoursPanel extends javax.swing.JPanel {

    Dialog dialog;
    String date;
    String docID;
    Statement stat;
    String startTime, stopTime;
    String where;

    /**
     * Creates new form DoctorHoursPanel
     */
    public DoctorHoursPanel(Dialog dialog, String date, String docID) {
        try {
            initComponents();
            this.dialog = dialog;
            this.date = date;
            this.docID = docID;
            
            where = "WHERE DoctorID = '" + docID + "' AND Date = '" + date + "'";
            //get existing record and/or delete
            stat = EHIS.getConnection().createStatement();
            String sql = "SELECT * FROM calendar " + where;
            ResultSet rs = stat.executeQuery(sql);
            

            //set up two time spinners
            spin_StartTime.setModel(new SpinnerDateModel());
            JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spin_StartTime, "H:mm");
            spin_StartTime.setEditor(timeEditor);


            spin_StopTime.setModel(new SpinnerDateModel());
            JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(spin_StopTime, "H:mm");
            spin_StopTime.setEditor(timeEditor2);

            if (rs.next()) {

                startTime = rs.getString("StartTime");
                stopTime = rs.getString("EndTime");
                spin_StartTime.setValue(new SimpleDateFormat("H:mm").parse(startTime));
                spin_StopTime.setValue(new SimpleDateFormat("H:mm").parse(stopTime));
                ;

            } else {
                spin_StopTime.setValue(new java.util.Date());
                spin_StartTime.setValue(new java.util.Date());
            }
            
            

        } catch (Exception ex) {
            Logger.getLogger(DoctorHoursPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        timeOfDay1 = new org.joda.time.TimeOfDay();
        spin_StartTime = new javax.swing.JSpinner();
        spin_StopTime = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_SubmitTimes = new javax.swing.JButton();

        jLabel1.setText("Start Time:");

        jLabel2.setText("End Time:");

        btn_SubmitTimes.setText("Submit");
        btn_SubmitTimes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubmitTimesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_SubmitTimes)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spin_StopTime, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spin_StartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spin_StartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spin_StopTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SubmitTimes)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SubmitTimesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubmitTimesActionPerformed
        try {
            stat.execute("DELETE FROM calendar " + where);
            String sql = "INSERT INTO calendar (DoctorID, Date, StartTime, EndTime) VALUES('" +
                    docID + "','" + 
                    date + "','" + 
                    TimeAndDate.dateToTimeString((Date)spin_StartTime.getValue())+ "','" + 
                    TimeAndDate.dateToTimeString((Date)spin_StopTime.getValue()) + "')";
            
                stat.execute(sql);
            dialog.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorHoursPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_SubmitTimesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SubmitTimes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner spin_StartTime;
    private javax.swing.JSpinner spin_StopTime;
    private org.joda.time.TimeOfDay timeOfDay1;
    // End of variables declaration//GEN-END:variables
}
