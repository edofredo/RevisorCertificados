/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataExtractorService;

import java.io.IOException;
import java.util.ArrayList;
import model.Sensor;

/**
 *
 * @author Cristian
 */
public interface DataParser {
    public ArrayList<Sensor> parser() throws IOException;
    public ArrayList<String> getCertificateErrorPaths() throws IOException;
}
