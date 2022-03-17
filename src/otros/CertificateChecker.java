/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import pdfManagers.PDFManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Sensor;
import model.SensorType;

/**
 *
 * @author Cristian
 */
public class CertificateChecker{
    
    String laboratorio;
    String sensor;
    String marca;
    String modelo;
    
    String parserToInvoque;
    
    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;
    
    public String parser() throws IOException {

        File file = new File("D:\\50_DAM\\01_SegundoCurso\\06_Proyecto\\02_CertificadosCalibracion\\Prueba\\Varios");
        String[] pathNames = file.list();
        
        
        for (String s : pathNames) {
            try {
                pdfManager = new PDFManager();
                absolutePath = file.getAbsolutePath() + File.separator;
                filePath = file.getAbsolutePath() + File.separator + s;
                pdfManager.setFilePath(filePath);

            String[] datos = pdfManager.getTextUsingPositionsUsingPdf(filePath, -1, 0, 0, 400, 400).split("\n");   
                laboratorio = datos[7].trim();
                
            if(laboratorio.equalsIgnoreCase("AC6 Metrología S.L.")){
                System.out.println("Entro en ManoLufft");
            }
            
            } catch (Exception e) {
                System.out.println("Error de fichero");
                
            }
                     
            
        }
        return parserToInvoque;
    }
}
