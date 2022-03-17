/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author CRA
 */
public class Sensor {
    
    private String serialNumber;
    private String slope;
    private String offset;
    private String calibrationDate;
    private double uncertainty;

    public Sensor() {
    }
       
    public Sensor(String slope, String offset, String serial, String date, double uncert) {
        this.slope = slope;
        this.offset = offset;
        this.serialNumber = serial;
        this.calibrationDate = date;
        this.uncertainty = uncert;
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

    public String getSerial() {
        return serialNumber;
    }

    public void setSerial(String serial) {
        this.serialNumber = serial;
    }

    public String getDate() {
        return calibrationDate;
    }

    public void setDate(String date) {
        this.calibrationDate = date;
    }

    public double getUncert() {
        return uncertainty;
    }

    public void setUncert(double uncert) {
        this.uncertainty = uncert;
    }
}
