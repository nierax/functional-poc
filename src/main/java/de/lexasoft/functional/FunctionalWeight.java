package de.lexasoft.functional;

import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FunctionalWeight {
	
	private Logger logger = Logger.getLogger(FunctionalWeight.class.getName());
	
public void pocWeight () {
	Function<Double, Function<Double, Double>> weight = gravity -> mass -> mass * gravity;

	Function<Double, Double> weightOnEarth = weight.apply(9.81);
	logger.log(Level.INFO, "My weight on Earth: " + weightOnEarth.apply(60.0));

	Function<Double, Double> weightOnMars = weight.apply(3.75);
	logger.log(Level.INFO, "My weight on Mars: " + weightOnMars.apply(60.0));
}

public static void main(String[] args) {
	new FunctionalWeight().pocWeight();
}


}
