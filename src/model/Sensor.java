/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author CRA
 */
public class Sensor {
    
    private String measurand;
    private String laboratory;
    private String serialNumber;
    private String model;
    private String slope;
    private String offset;
    private String calibrationDate;
    private double uncertainty;

    public Sensor() {
    }
    
    public Sensor(String measurand, String laboratory, String serialNumber, String slope, String offset, String calibrationDate, double uncertainty) {
        this.measurand = measurand;
        this.laboratory = laboratory;
        this.serialNumber = serialNumber;
        this.slope = slope;
        this.offset = offset;
        this.calibrationDate = calibrationDate;
        this.uncertainty = uncertainty;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }
    
    public String getMeasurand() {
        return measurand;
    }

    public void setMeasurand(String measurand) {
        this.measurand = measurand;
    }
            
    public String getSlope() {
        return slope;
    }

    public void setSlope(String slope) {
        this.slope = slope;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serial) {
        this.serialNumber = serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCalibrationDate() {
        return calibrationDate;
    }

    public void setCalibrationDate(String date) {
        this.calibrationDate = date;
    }   
    
    public double getUncertainty() {
        return uncertainty;
    }

    public void setUncertainty(double uncert) {
        DecimalFormat df = new DecimalFormat("0.0000");
        double u = Double.parseDouble(df.format(uncert));
        this.uncertainty = u;
    }
}
