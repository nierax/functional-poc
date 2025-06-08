package de.lexasoft.functional;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Factorial {
	
	private static Logger logger = Logger.getLogger(Factorial.class.getName());

	public Integer factorial(Integer number) {
		return (number == 1) ? 1 : number * factorial(number - 1);
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, new Factorial().factorial(3).toString());
	}
}
