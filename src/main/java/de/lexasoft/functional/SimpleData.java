package de.lexasoft.functional;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleData {
    private static Logger logger = Logger.getGlobal();
    private String data;
    public String getData() {
        logger.log(Level.INFO, "Get data called for SimpleData");
        return data;
    }
    public SimpleData setData(String data) {
        logger.log(Level.INFO, "Set data called for SimpleData");
        this.data = data;
        return this;
    }
    
    public final static void main(String[] args) {
    	String data = new SimpleData().setData("Baeldung").getData();
    	logger.log(Level.INFO, new SimpleData().setData("Baeldung").getData());
    	// Replacing this will miss the log in the class
    	// logger.log(Level.INFO, "Baeldung");
    	logger.log(Level.INFO, data);
    	logger.log(Level.INFO, "Baeldung");
    }
}