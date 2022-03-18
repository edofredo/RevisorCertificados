/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataExtractorService;

import laboratoryDWG.DWGType000;
import laboratoryDWG.DWGType400;
import laboratoryIDR.IDRType000;
import laboratoryAC6.AC6BaroLufftWs300;
import laboratoryAC6.AC6THLufftWs300;

/**
 *
 * @author Cristian
 */
public class DataParserSelector {
    
    private int laboratorySelection;
    private int sensorSelection;
    private String path;

    private DataParser dataparser;
    
    public DataParserSelector(int laboratorySelection, int sensorSelection, String path) {
        this.laboratorySelection = laboratorySelection;
        this.sensorSelection = sensorSelection;
        this.path = path;
    }

    public DataParser selectParser(){
        
        if(laboratorySelection==1 & sensorSelection == 1){
            IDRType000 parser = new IDRType000();
            parser.setGuiPath(path);
            dataparser = (DataParser)parser;
        } else if (laboratorySelection==2 & sensorSelection == 1){
            DWGType000 parser = new DWGType000();
            parser.setGuiPath(path);
            dataparser = (DataParser)parser;
        }   else if (laboratorySelection==2 & sensorSelection == 2){
            DWGType400 parser = new DWGType400();
            parser.setGuiPath(path);
            dataparser = (DataParser)parser;
        } else if (laboratorySelection==3 & sensorSelection == 1){
            AC6BaroLufftWs300 parser = new AC6BaroLufftWs300();
            parser.setGuiPath(path);
            dataparser = (DataParser)parser;
        } else if (laboratorySelection==3 & sensorSelection == 2){
            AC6THLufftWs300 parser = new AC6THLufftWs300();
            parser.setGuiPath(path);
            dataparser = (DataParser)parser;
        }
        return dataparser;
    }
    
    public int getLaboratorySelection() {
        return laboratorySelection;
    }

    public void setLaboratorySelection(int laboratorySelection) {
        this.laboratorySelection = laboratorySelection;
    }

    public int getSensorSelection() {
        return sensorSelection;
    }

    public void setSensorSelection(int sensorSelection) {
        this.sensorSelection = sensorSelection;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DataParser getDataparser() {
        return dataparser;
    }

    public void setDataparser(DataParser dataparser) {
        this.dataparser = dataparser;
    }
    
        
}
