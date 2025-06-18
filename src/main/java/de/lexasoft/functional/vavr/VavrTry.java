/**
 * 
 */
package de.lexasoft.functional.vavr;

import io.vavr.control.Try;

/**
 * 
 */
public class VavrTry {
  // = Success(result) or Failure(exception)
  Try<Integer> divide(Integer dividend, Integer divisor) {
    return Try.of(() -> dividend / divisor);
  }

  public final static void main(String[] args) {
    VavrTry vavrTry = new VavrTry();
    Try<Integer> result = vavrTry.divide(2, 2);
    result.get();
  }

}
