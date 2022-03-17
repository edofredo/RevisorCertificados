/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public interface SensorType {
    public ArrayList<Sensor> parser() throws IOException;
}
