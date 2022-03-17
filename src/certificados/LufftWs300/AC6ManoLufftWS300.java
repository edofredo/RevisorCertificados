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
public class AC6ManoLufftWS300 implements DataParser {

    private String serialNumber;
    private String slope;
    private String offset;
    private String calibrationDate;
    private double uncertainty;

    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;

    private Object ArrayUtils;
    ArrayList<Sensor> sensorList = new ArrayList<>();
    Sensor sensor = null;

    @Override
    public ArrayList<Sensor> parser() throws IOException {
        File file = new File("D:\\50_DAM\\01_SegundoCurso\\06_Proyecto\\02_CertificadosCalibracion\\AC6\\TypeLufftManometro");
        String[] pathNames = file.list();

        for (String s : pathNames) {

            sensor = new Sensor();
            pdfManager = new PDFManager();
            absolutePath = file.getAbsolutePath() + File.separator;
            filePath = file.getAbsolutePath() + File.separator + s;
            pdfManager.setFilePath(filePath);

            String[] datosSensor = pdfManager.getTextUsingPositionsUsingPdf(filePath, -1, 0, 350, 400, 250).split("\n");
            serialNumber = datosSensor[2].substring(15, 32).trim();
            calibrationDate = datosSensor[7].substring(21, 42).trim();
            //System.out.println(serialNumber);
            //System.out.println(calibrationDate);

            double[] incertidumbre = new double[5];
            String[] datosIncertidumbre = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 350, 300, 100, 120).split("\n");
            for (int i = 0; i <= datosIncertidumbre.length - 1; i++) {
                double dato = Double.parseDouble(datosIncertidumbre[i].replace(",", "."));
                incertidumbre[i] = dato;
                //System.out.println(incertidumbre[i]);
            }

            double[] correccion = new double[5];
            String[] datosCorreccion = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 300, 300, 50, 140).split("\n");
            for (int i = 0; i <= datosCorreccion.length - 1; i++) {
                double dato = Math.abs(Double.parseDouble(datosCorreccion[i].replace(",", ".")));
                correccion[i] = dato;
                //System.out.println(correccion[i]);
            }

            double[] resultado = new double[5];
            for (int i = 0; i <= incertidumbre.length - 1; i++) {
                resultado[i] = correccion[i] + incertidumbre[i];
                //System.out.println(resultado[i]);
            }
            
            Arrays.sort(resultado);
            uncertainty = resultado[4];
            
            sensor.setSerial(serialNumber);
            sensor.setOffset("0");
            sensor.setDate(calibrationDate);
            sensor.setSlope("1");
            sensor.setUncert(uncertainty);

            sensorList.add(sensor);
                        
            Path copy = Paths.get(absolutePath + serialNumber + "Mano.pdf");
            Path original = Paths.get(filePath);
            Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);

            
            // System.out.println("Resultados: " + "\n" + serialNumber+ " / "+ calibrationDate +" / "+ uncertainty );
            
            
            
            //dataForInventory = dataForInventory + serialNumber + " / " + calibrationDate + " / " + String.format("%.2f", uncertainty) + "\n";
        }

        return sensorList;
        //System.out.println("Data for Inventory\n" + dataForInventory);
    }
}
