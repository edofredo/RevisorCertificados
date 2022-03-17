/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package certificates.General;

import java.io.IOException;
import java.util.ArrayList;
import model.Sensor;
import pdfManagers.PDFManager;

/**
 *
 * @author Cristian
 */
public class Laboratory {
           
    private String laboratorioCalibracion;
    private String marcaSensor;
    private String modeloSensor;

    private PDFManager pdfManager;
    private String absolutePath;
    private String filePath;

    private Object ArrayUtils;
    private ArrayList<Sensor> sensorList = new ArrayList<>();
    private Sensor sensor;

            
    public String getLaboratorioCalibracion() {
        return laboratorioCalibracion;
    }

    public void setLaboratorioCalibracion(String laboratorioCalibracion) {
        this.laboratorioCalibracion = laboratorioCalibracion;
    }

    public String getMarcaSensor() {
        return marcaSensor;
    }

    public void setMarcaSensor(String marcaSensor) {
        this.marcaSensor = marcaSensor;
    }

    public String getModeloSensor() {
        return modeloSensor;
    }

    public void setModeloSensor(String modeloSensor) {
        this.modeloSensor = modeloSensor;
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

    public Object getArrayUtils() {
        return ArrayUtils;
    }

    public void setArrayUtils(Object ArrayUtils) {
        this.ArrayUtils = ArrayUtils;
    }

    public ArrayList<Sensor> getSensorList() {
        sensorList = new ArrayList<>();
        return sensorList;
    }

    public void setSensorList(ArrayList<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    public Sensor getSensor() {
        sensor = new Sensor();
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    
    
    
}
