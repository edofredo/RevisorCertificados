/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataExtractorService;

import gui.Tests;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class DateFormater {
    
    public static String formatAc6ToStandart(String date){
        Date datePattern = null; 
        try {
            datePattern = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",new Locale("es")).parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateFormat formato = DateFormat.getDateInstance(DateFormat.SHORT);
        String dateEN = formato.format(datePattern);
        System.out.println(dateEN);
        String standartDate = formatDateToStandart(dateEN);
        return standartDate;
    }
    
    private static String formatDateToStandart(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormater.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf.applyPattern("yyyy-MM-dd");
        String formatedDate = sdf.format(d);
        return formatedDate;
    }
    
    public static String formatDwgToStandart(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormater.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf.applyPattern("yyyy-MM-dd");
        String formatedDate = sdf.format(d);
        return formatedDate;
    }
    
    public static String formatIdrToStandart(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormater.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf.applyPattern("yyyy-MM-dd");
        String formatedDate = sdf.format(d);
        return formatedDate;
    }
}
