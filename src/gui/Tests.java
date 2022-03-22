/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import laboratoryDWG.DWGType400;
import dataExtractorService.DataParser;
import dataExtractorService.DateFormater;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class Tests {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        System.out.println(DateFormater.formatIdrToStandart("January 27, 2022"));
                
    }          
}
