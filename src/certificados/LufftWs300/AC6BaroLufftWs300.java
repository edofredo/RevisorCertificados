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
import certificates.General.DataParser;

/**
 *
 * @author CRA
 */
public class AC6BaroLufftWs300 implements DataParser {

    private Sensor sensor;
    private final ArrayList<Sensor> sensorList = new ArrayList<>();
    
    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;

    @Override
    public ArrayList<Sensor> parser() throws IOException {
        File file = new File("D:\\50_DAM\\01_SegundoCurso\\06_Proyecto\\02_CertificadosCalibracion\\Prueba\\AC6\\TypeLufftManometro");
        String[] pathNames = file.list();

        for (String s : pathNames) {

            sensor = new Sensor();
            pdfManager = new PDFManager();
            absolutePath = file.getAbsolutePath() + File.separator;
            filePath = file.getAbsolutePath() + File.separator + s;
            pdfManager.setFilePath(filePath);

            String[] calibrationData = pdfManager.getTextUsingPositionsUsingPdf(filePath, -1, 0, 0, 500, 600).split("\n");
            
            double[] incertidumbre = new double[5];
            String[] datosIncertidumbre = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 350, 300, 100, 120).split("\n");
            for (int i = 0; i <= datosIncertidumbre.length - 1; i++) {
                double dato = Double.parseDouble(datosIncertidumbre[i].replace(",", "."));
                incertidumbre[i] = dato;
            }

            double[] correccion = new double[5];
            String[] datosCorreccion = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 300, 300, 50, 140).split("\n");
            for (int i = 0; i <= datosCorreccion.length - 1; i++) {
                double dato = Math.abs(Double.parseDouble(datosCorreccion[i].replace(",", ".")));
                correccion[i] = dato;
            }

            double[] resultado = new double[5];
            for (int i = 0; i <= incertidumbre.length - 1; i++) {
                resultado[i] = correccion[i] + incertidumbre[i];
            }
            Arrays.sort(resultado);       
            
            sensor.setMeasurand("Barometer");
            sensor.setLaboratory(calibrationData[7].substring(0, 3).trim());
            sensor.setSerialNumber(calibrationData[19].substring(15, 32).trim());
            sensor.setOffset("0");
            sensor.setCalibrationDate(calibrationData[24].substring(21, 42).trim());
            sensor.setSlope("1");
            sensor.setUncertainty(resultado[4]);

            sensorList.add(sensor);
                        
            Path copy = Paths.get(absolutePath + "AC6_" + sensor.getSerialNumber() + "BaroLufftWs300.pdf");
            Path original = Paths.get(filePath);
            Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);
        }
        return sensorList;
    }
}
