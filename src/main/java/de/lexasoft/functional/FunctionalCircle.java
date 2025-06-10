/**
 * 
 */
package de.lexasoft.functional;

import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class FunctionalCircle {

  private static Logger logger = Logger.getLogger(FunctionalCircle.class.getName());

  public final static Function<Double, Double> ference = diameter -> Math.PI * diameter;
  public final static Function<Double, Double> radius = diameter -> diameter / 2;
  public final static Function<Double, Double> area = diameter -> Math.PI * Math.pow((radius.apply(diameter)),2);
  
  public final static void main(String[] args) {
    logger.log(Level.INFO,ference.apply(3d).toString());
    logger.log(Level.INFO,area.apply(3d).toString());
  }

}
