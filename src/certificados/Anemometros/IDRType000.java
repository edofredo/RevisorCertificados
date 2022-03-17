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
import model.SensorType;

/**
 *
 * @author CRA
 */
public class IDRType000 implements SensorType{

    private String serialNumber;
    private String slope;
    private String offset;
    private String calibrationDate;
    private double uncertainty;
    
    private String laboratorioCalibracion;
    private String marcaSensor;
    private String modeloSensor;

    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;

    private Object ArrayUtils;
    ArrayList<Sensor> sensorList = new ArrayList<>();
    Sensor sensor;
    
       
    @Override
    public ArrayList<Sensor> parser() throws IOException {

        File file = new File("D:\\50_DAM\\01_SegundoCurso\\06_Proyecto\\02_CertificadosCalibracion\\Prueba\\Varios");
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
            /*
            for(String valor : tablaUncert){
                System.out.println(valor);
            }*/
                        
            String[] tablaSlopOff = pdfManager.getTextUsingPositionsUsingPdf(filePath, 2, 160, 300, 80, 80).split("\n");
            /*
            for(String valor : tablaSlopOff){
                System.out.println(valor);
            }
             */            
            System.out.println(laboratorioCalibracion = datos[3].substring(0, 40).trim());
            System.out.println(marcaSensor = datos[11].substring(5,11).trim());
            System.out.println(modeloSensor = datos[13].substring(6, 20).trim());
            System.out.println(serialNumber = datos[15].substring(14, 23).trim());
            System.out.println(calibrationDate = datos[20].substring(20, 37).trim());
            System.out.println(slope = tablaSlopOff[0].substring(0, 7));
            System.out.println(offset = tablaSlopOff[1].substring(0, 7));
            System.out.println(uncertainty = Double.parseDouble(tablaUncert[12]) / 2);

            sensor.setSerial(serialNumber);
            sensor.setOffset(offset);
            sensor.setDate(calibrationDate);
            sensor.setSlope(slope);
            sensor.setUncert(uncertainty);

            sensorList.add(sensor);

            Path copy = Paths.get(absolutePath + "IDR_" + serialNumber + "_Type000.pdf");
            Path original = Paths.get(filePath);
            Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);

                
            } catch (Exception e) {
                System.out.println("Error en tipo certificado/ " + e.getMessage());
            }
            
            
        }

        for(Sensor s : sensorList){
            System.out.println(s.getSerial());
        }
        
        return sensorList;

    }
}
