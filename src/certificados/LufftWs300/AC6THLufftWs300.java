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
import java.text.DecimalFormat;

/**
 *
 * @author CRA
 */
public class AC6THLufftWs300 implements DataParser {

    DecimalFormat df = new DecimalFormat("0.00");
    private Sensor sensor;
    private final ArrayList<Sensor> sensorList = new ArrayList<>();

    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;
    private String guiPath;

    @Override
    public ArrayList<Sensor> parser() throws IOException {
        File file = new File(guiPath);
        String[] pathNames = file.list();
        for (String s : pathNames) {
            sensor = new Sensor();
            pdfManager = new PDFManager();
            absolutePath = file.getAbsolutePath() + File.separator;
            filePath = file.getAbsolutePath() + File.separator + s;
            pdfManager.setFilePath(filePath);

            try {
                //----General calibration data-----------------------------------------
                String[] calibrationData = pdfManager
                        .getTextUsingPositionsUsingPdf(filePath, -1, 0, 0, 500, 600).split("\n");

                sensor.setMeasurand("Temperature");
                sensor.setLaboratory(calibrationData[8].substring(0, 3).trim());
                sensor.setSerialNumber(calibrationData[21].substring(15, 32).trim());
                sensor.setOffset("0");
                sensor.setCalibrationDate(calibrationData[26].substring(21, 42).trim());
                sensor.setSlope("1");

                //----Data for Temperature -------------o------------------------------
                double[] correccionT = new double[5];
                String[] datosCorreccion = pdfManager
                        .getTextUsingPositionsUsingPdf(filePath, 1, 350, 330, 20, 120).split("\n");

                for (int i = 0; i <= datosCorreccion.length - 1; i++) {
                    double dato = Double.parseDouble(datosCorreccion[i].replace(",", "."));
                    correccionT[i] = dato;
                    System.out.println(correccionT[i]);
                }

                double[] incertidumbreT = new double[5];
                String[] datosIncertidumbre = pdfManager
                        .getTextUsingPositionsUsingPdf(filePath, 1, 400, 330, 90, 130).split("\n");

                for (int i = 0; i <= datosIncertidumbre.length - 1; i++) {
                    double dato = Math.abs(Double.parseDouble(datosIncertidumbre[i].replace(",", ".")));
                    incertidumbreT[i] = dato;
                }

                double[] resultadoT = new double[5];
                for (int i = 0; i <= incertidumbreT.length - 1; i++) {
                    resultadoT[i] = incertidumbreT[i] + correccionT[i];
                }
                Arrays.sort(resultadoT);
                sensor.setUncertainty(resultadoT[4]);

                sensorList.add(sensor);

                //----Data for Humidity & general info----------------------------------
                double[] correccionH = new double[4];
                String[] datosCorreccionH = pdfManager
                        .getTextUsingPositionsUsingPdf(filePath, 1, 350, 650, 80, 150).split("\n");

                for (int i = 0; i <= datosCorreccionH.length - 1; i++) {
                    double datoH = Math.abs(Double.parseDouble(datosCorreccionH[i].replace(",", ".")));
                    correccionH[i] = datoH;
                    System.out.println(correccionH[i]);
                }

                double[] incertidumbreH = new double[4];
                String[] datosIncertidumbreH = pdfManager
                        .getTextUsingPositionsUsingPdf(filePath, 1, 450, 650, 80, 150).split("\n");

                for (int i = 0; i <= datosIncertidumbreH.length - 1; i++) {
                    double datoH = Math.abs(Double.parseDouble(datosIncertidumbreH[i].replace(",", ".")));
                    incertidumbreH[i] = datoH;
                    System.out.println(incertidumbreH[i]);
                }

                double[] resultadoH = new double[4];
                for (int i = 0; i <= incertidumbreH.length - 1; i++) {
                    resultadoH[i] = incertidumbreH[i] + correccionH[i];
                    System.out.println(resultadoH[i]);
                }
                Arrays.sort(resultadoH);

                sensor = new Sensor();

                sensor.setMeasurand("Humidity");
                sensor.setLaboratory(calibrationData[8].substring(0, 3).trim());
                sensor.setSerialNumber(calibrationData[21].substring(15, 32).trim());
                sensor.setOffset("0");
                sensor.setCalibrationDate(calibrationData[26].substring(21, 42).trim());
                sensor.setSlope("1");
                sensor.setUncertainty(resultadoH[3]);
                sensorList.add(sensor);

                String newPath = absolutePath + "AC6THLufft" + File.separator;
                File newFolder = new File(newPath);
                if (!newFolder.exists()) {
                    newFolder.mkdirs();
                }
                Path copy = Paths.get(newPath + sensor.getSerialNumber() + "_TermoHygroLufftWS300.pdf");
                Path original = Paths.get(filePath);
                Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                System.out.println("Certificate type error: " + e.getMessage());
            }

        }
        return sensorList;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public PDFManager getPdfManager() {
        return pdfManager;
    }

    public void setPdfManager(PDFManager pdfManager) {
        this.pdfManager = pdfManager;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getGuiPath() {
        return guiPath;
    }

    public void setGuiPath(String guiPath) {
        this.guiPath = guiPath;
    }

}
