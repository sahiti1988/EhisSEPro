/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ehis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Joe
 */
public class TimeAndDate {
    public static String dateToDateString(Date date){
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
    }
    public static String dateToTimeString(Date date){
        return new SimpleDateFormat("H:mm").format(date);
    }
    public static Date timeStringToDate(String time){
        try {
            return new SimpleDateFormat("H:mm").parse(time);
        } catch (ParseException ex) {
            Logger.getLogger(TimeAndDate.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
