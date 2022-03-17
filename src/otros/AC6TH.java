/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import pdfManagers.PDFManager;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author CRA
 */
public class AC6TH {

    private PDFManager pdfManager;
    private String path;
    double totalUncertainityT;
    String serialNumber, date;
    String dataForInventory = "";
    
        
    public void parser() throws IOException{
        File f = new File("C:\\Users\\CRA\\Desktop\\CertTemp\\Prueba\\Mipelsa");
        String[] pathNames = f.list();
        for(String s : pathNames){
        
        pdfManager = new PDFManager();       
        path = f.getAbsolutePath()+File.separator+s;
        pdfManager.setFilePath(path);
                  
           
        String[] datosSensor = pdfManager.getTextUsingPositionsUsingPdf(path, -1, 0, 350, 400, 250).split("\n");
        serialNumber = datosSensor[3].substring(15, 32).trim();
        date = datosSensor[8].substring(21,41).trim();
            System.out.println(serialNumber + date);
            //System.out.println(serialNumber);
            //System.out.println(date);
              
        double[] correccionT = new double[5];
        String[] datosCorreccion = pdfManager.getTextUsingPositionsUsingPdf(path, 1, 350, 330, 20, 120).split("\n");
        /*
        for(String str : datosIncertidumbre){
            System.out.println(str);
        }
         */
        for (int i = 0; i <= datosCorreccion.length - 1; i++) {
            double dato = Double.parseDouble(datosCorreccion[i].replace(",", "."));
            correccionT[i] = dato;
            System.out.println(correccionT[i]);
        }
       
        double[] incertidumbreT= new double[5];
        String[] datosIncertidumbre = pdfManager.getTextUsingPositionsUsingPdf(path, 1, 400, 330, 90, 130).split("\n");
        /*
        for(String str : datosCorreccion){
            System.out.println(str);
        }
        */
        for(int i=0; i<=datosIncertidumbre.length-1;i++){
            double dato = Math.abs(Double.parseDouble(datosIncertidumbre[i].replace(",", ".")));
            incertidumbreT[i]=dato;
            System.out.println(incertidumbreT[i]);
        }
        
        double[] resultadoT = new double[5];
        for (int i = 0; i <= incertidumbreT.length - 1; i++) {
            resultadoT[i] = incertidumbreT[i] + correccionT[i];
            //System.out.println(resultado[i]);
        }
        
        //--------------------------------------------------------------------------------------
        
        double[] correccionH = new double[4];
        String[] datosCorreccionH = pdfManager.getTextUsingPositionsUsingPdf(path, 1, 350, 650, 80, 150).split("\n");
        
        for(String str : datosCorreccionH){
            System.out.println(str);
        }
        /*
        for (int i = 0; i <= datosCorreccionH.length - 1; i++) {
            double dato = Double.parseDouble(datosCorreccionH[i].replace(",", "."));
            correccionH[i] = dato;
            System.out.println(correccionH[i]);
        }
        
        double[] incertidumbreH= new double[4];
        String[] datosIncertidumbreH = pdfManager.getTextUsingPositionsUsingPdf(path, 1, 450, 650, 80, 150).split("\n");
        
        for(String str : datosCorreccion){
            System.out.println(str);
        }
        
        for(int i=0; i<=datosIncertidumbreH.length-1;i++){
            double dato = Math.abs(Double.parseDouble(datosIncertidumbreH[i].replace(",", ".")));
            incertidumbreH[i]=dato;
            System.out.println(incertidumbreH[i]);
        }
        
        double[] resultadoH = new double[5];
        for (int i = 0; i <= incertidumbreH.length - 1; i++) {
            resultadoH[i] = incertidumbreH[i] + correccionH[i];
            //System.out.println(resultado[i]);
        }
        
        //-------------------------------------------------------------------------------------
        
        Arrays.sort(resultadoH);
        totalUncertainityT = resultadoH[4];
       // System.out.println("Resultados: " + "\n" + serialNumber+ " / "+ date +" / "+ totalUncertainity );
        
       
       
       
       
       
        dataForInventory = dataForInventory + serialNumber+ " / "+ date +" / "+ "Temp: " + totalUncertainityT + "\n";
        System.out.println("Data for Inventory\n" + dataForInventory);
*/
        }
    }
    
}
