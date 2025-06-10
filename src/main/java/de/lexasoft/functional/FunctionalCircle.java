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
  public final static Function<Double, Double> area = radius -> Math.PI * Math.pow(radius, 2);
  public final static Function<Double, Double> areaFromDiameter = area.compose(radius);

  public final static void main(String[] args) {
    Double d = 3d;
    logger.log(Level.INFO, ference.apply(d).toString());
    logger.log(Level.INFO, area.apply(radius.apply(d)).toString());
    logger.log(Level.INFO, areaFromDiameter.apply(d).toString());
  }

}
