/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoryDWG;

import dataExtractorService.PDFManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import model.Sensor;
import dataExtractorService.DataParser;
import dataExtractorService.DateFormater;

/**
 *
 * @author CRA
 */
public class DWGType000 implements DataParser {

    private Sensor sensor;
    private ArrayList<Sensor> sensorList = new ArrayList<>();
    private ArrayList<String> certificateErrorPaths = new ArrayList<>();

    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;
    private String guiPath;

    @Override
    public ArrayList<Sensor> parser() throws IOException {

        File file = new File(guiPath);
        String[] pathNames = file.list();
        for (String fileName : pathNames) {

            sensor = new Sensor();
            pdfManager = new PDFManager();
            absolutePath = file.getAbsolutePath() + File.separator;
            filePath = file.getAbsolutePath() + File.separator + fileName;
            if (filePath.endsWith(".pdf")) {
                pdfManager.setFilePath(filePath);

                try {

                    String[] datos = pdfManager.getTextUsingPositionsUsingPdf(filePath, -1, 0, 100, 300, 500).split("\n");

                    String[] tablaUncert = pdfManager.getTextUsingPositionsUsingPdf(filePath, 1, 270, 220, 50, 400).split("\n");
                    Arrays.sort(tablaUncert);

                    String[] tablaSlopOff = pdfManager.getTextUsingPositionsUsingPdf(filePath, 2, 300, 400, 150, 120).split("\n");

                    sensor.setMeasurand("Windspeed");
                    sensor.setLaboratory(datos[2].substring(0, 18).trim());
                    if (!sensor.getLaboratory().equalsIgnoreCase("Deutsche WindGuard")) {
                        throw new Exception("Not a DWG certificate.");
                    }
                    sensor.setModel(datos[12].substring(14, 18).trim());
                    if (!sensor.getModel().equalsIgnoreCase(".000")) {
                        throw new Exception("Not a type .000");
                    }
                    sensor.setSerialNumber(datos[14].substring(14, 23).trim());
                    sensor.setCalibrationDate(DateFormater.formatDwgToStandart(datos[24].substring(20, 31).trim()));
                    sensor.setSlope(tablaSlopOff[0].substring(0, 7));
                    sensor.setOffset(tablaSlopOff[1].substring(0, 6));
                    sensor.setUncertainty(Double.parseDouble(tablaUncert[12]) / 2);

                    sensorList.add(sensor);
                    String newPath = absolutePath + "DWG" + File.separator;
                    File newFolder = new File(newPath);
                    if (!newFolder.exists()) {
                        newFolder.mkdirs();
                    }
                    Path copy = Paths.get(newPath + "DWG_" + sensor.getSerialNumber() + "_Type000.pdf");
                    Path original = Paths.get(filePath);
                    Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);

                } catch (Exception e) {
                    certificateErrorPaths.add(fileName);
                    System.out.println("Certificate type error: " + e.getMessage());
                }
            }
        }

        for (Sensor s : sensorList) {
            System.out.println(s.getSerialNumber());
        }

        return sensorList;

    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public ArrayList<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(ArrayList<Sensor> sensorList) {
        this.sensorList = sensorList;
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

    @Override
    public ArrayList<String> getCertificateErrorPaths() throws IOException {
        return certificateErrorPaths;
    }

}
