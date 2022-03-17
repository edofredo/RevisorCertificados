/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfManagers;

import certificados.Anemometros.IDRType000;
import certificados.Anemometros.WindguardType000;
import certificados.LufftWs300.AC6ManoLufftWS300;
import certificados.LufftWs300.AC6ThermoHygroLufftWS300;
import java.io.IOException;

/**
 *
 * @author CRA
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //pruebaIDR();
        //pruebaWG();
        //pruebaLufftM();
        pruebaLufftTH();
        
    }
    
    public static void pruebaIDR() throws IOException{
        IDRType000 idrA = new IDRType000();
        idrA.parser();
    }
    
    public static void pruebaWG() throws IOException{
        WindguardType000 wg = new WindguardType000();
        wg.parser();
    }
    
    public static void pruebaLufftM() throws IOException{
        AC6ManoLufftWS300 ac6 = new AC6ManoLufftWS300();
        ac6.parser();
    }
    
    public static void pruebaLufftTH() throws IOException{
        AC6ThermoHygroLufftWS300 ac6 = new AC6ThermoHygroLufftWS300();
        ac6.parser();
    }        
}
