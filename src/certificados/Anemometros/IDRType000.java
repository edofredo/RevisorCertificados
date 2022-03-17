/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package certificados.Anemometros;

import pdfManagers.PDFManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import model.Sensor;
import certificates.General.DataParser;

/**
 *
 * @author CRA
 */
public class IDRType000 implements DataParser{
    
    private Sensor sensor;
    private ArrayList<Sensor> sensorList = new ArrayList<>();
    
    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;
    
    @Override
    public ArrayList<Sensor> parser() throws IOException {

        File file = new File("D:\\50_DAM\\01_SegundoCurso\\06_Proyecto\\02_CertificadosCalibracion\\Prueba\\IDR");
        String[] pathNames = file.list();
        
        for (String s : pathNames) {
            
            sensor = new Sensor();
            pdfManager = new PDFManager();
            absolutePath = file.getAbsolutePath() + File.separator;
            filePath = file.getAbsolutePath() + File.separator + s;
            pdfManager.setFilePath(filePath);

            try {
            
            String[] datos = pdfManager.getTextUsingPositionsUsingPdf(filePath, -1, 0, 100, 350, 400).split("\n");

            String[] tablaUncert = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 300, 350, 50, 230).split("\n");
            Arrays.sort(tablaUncert);                       
            
            String[] tablaSlopOff = pdfManager.getTextUsingPositionsUsingPdf(filePath, 2, 160, 300, 80, 80).split("\n");
            
            sensor.setMeasurand("Windspeed");
            sensor.setLaboratory(datos[4].substring(54, 63).trim());
            sensor.setSerialNumber(datos[15].substring(14, 23).trim());
            sensor.setCalibrationDate(datos[20].substring(20, 37).trim());
            sensor.setSlope(tablaSlopOff[0].substring(0, 7));
            sensor.setOffset(tablaSlopOff[1].substring(0, 7));
            sensor.setUncertainty(Double.parseDouble(tablaUncert[12]) / 2);
           
            sensorList.add(sensor);

            Path copy = Paths.get(absolutePath + "IDR_" + sensor.getSerialNumber() + "_Type000.pdf");
            Path original = Paths.get(filePath);
            Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);     
            } catch (Exception e) {
                System.out.println("Certificate type error" + e.getMessage());
            }
        }
        for(Sensor s : sensorList){
            System.out.println(s.getSerialNumber());
        }
        return sensorList;
    }
}
