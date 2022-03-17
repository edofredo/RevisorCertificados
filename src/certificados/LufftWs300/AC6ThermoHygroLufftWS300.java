/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package certificados.LufftWs300;

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
public class AC6ThermoHygroLufftWS300 implements SensorType {

    private String serialNumber;
    private String slope;
    private String offset;
    private String calibrationDate;
    double uncertaintyT;
    double uncertaintyH;

    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;

    private Object ArrayUtils;
    ArrayList<Sensor> sensorList = new ArrayList<>();
    Sensor sensorT = null;
    Sensor sensorH = null;
    
    @Override
    public ArrayList<Sensor> parser() throws IOException{
        File file = new File("D:\\50_DAM\\01_SegundoCurso\\06_Proyecto\\02_CertificadosCalibracion\\Prueba\\AC6\\TypeLufftTH");
        String[] pathNames = file.list();
        for(String s : pathNames){
        
        sensorT = new Sensor();
        sensorH = new Sensor();
        pdfManager = new PDFManager();       
        absolutePath = file.getAbsolutePath()+File.separator;
        filePath = file.getAbsolutePath()+File.separator+s;
        pdfManager.setFilePath(filePath);
                  
           
        String[] datosSensor = pdfManager.getTextUsingPositionsUsingPdf(filePath, -1, 0, 350, 400, 250).split("\n");
        serialNumber = datosSensor[3].substring(15, 32).trim();
        calibrationDate = datosSensor[8].substring(21,42).trim();
            System.out.println(serialNumber + calibrationDate);
            //System.out.println(serialNumber);
            //System.out.println(calibrationDate);
              
        double[] correccionT = new double[5];
        String[] datosCorreccion = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 350, 330, 20, 120).split("\n");
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
        String[] datosIncertidumbre = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 400, 330, 90, 130).split("\n");
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
        
        Arrays.sort(resultadoT);
        uncertaintyT = resultadoT [4];
        
        sensorT.setSerial(serialNumber);
        sensorT.setOffset("0");
        sensorT.setDate(calibrationDate);
        sensorT.setSlope("1");
        sensorT.setUncert(uncertaintyT);

        sensorList.add(sensorT);
        
        //--------------------------------------------------------------------------------------
        
        double[] correccionH = new double[4];
        String[] datosCorreccionH = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 350, 650, 80, 150).split("\n");
        /*
        for(String str : datosCorreccionH){
            System.out.println(str);
        }
        */
        for (int i = 0; i <= datosCorreccionH.length - 1; i++) {
            double datoH = Math.abs(Double.parseDouble(datosCorreccionH[i].replace(",", ".")));
            correccionH[i] = datoH;
            System.out.println(correccionH[i]);
        }
        
        double[] incertidumbreH= new double[4];
        String[] datosIncertidumbreH = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 450, 650, 80, 150).split("\n");
        /*
        for(String str : datosCorreccion){
            System.out.println(str);
        }
        */
        for(int i=0; i<=datosIncertidumbreH.length-1;i++){
            double datoH = Math.abs(Double.parseDouble(datosIncertidumbreH[i].replace(",", ".")));
            incertidumbreH[i]=datoH;
            System.out.println(incertidumbreH[i]);
        }
        
        double[] resultadoH = new double[4];
        for (int i = 0; i <= incertidumbreH.length - 1; i++) {
            resultadoH[i] = incertidumbreH[i] + correccionH[i];
            System.out.println(resultadoH[i]);
        }
        
        Arrays.sort(resultadoH);
            System.out.println(uncertaintyH = resultadoH [3]);
        
        sensorH.setSerial(serialNumber);
        sensorH.setOffset("0");
        sensorH.setDate(calibrationDate);
        sensorH.setSlope("1");
        sensorH.setUncert(uncertaintyH);

        sensorList.add(sensorH);
                
        Path copy = Paths.get(absolutePath+"AC6_"+ serialNumber + "_TermoHygroLufftWS300.pdf");
        Path original = Paths.get(filePath);
        Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);
        
        }
        return sensorList;
    }
    
}
