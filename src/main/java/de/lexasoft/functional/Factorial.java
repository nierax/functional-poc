package de.lexasoft.functional;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Factorial {
	
	private static Logger logger = Logger.getLogger(Factorial.class.getName());

	public Integer factorial(Integer number, Integer result) {
		return (number == 1) ? result : factorial(number - 1, result * number);
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, new Factorial().factorial(6, 1).toString());
	}
}
