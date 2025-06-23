/**
 * 
 */
package de.lexasoft.functional.vavr;

import java.util.function.BiPredicate;

import io.vavr.collection.CharSeq;

/**
 * Some standard validations, which are often used
 */
public class Validations {

  public final static BiPredicate<String, Integer> maxLength = (v, m) -> v.length() <= m;
  public final static BiPredicate<String, String> allowedCharacters = //
      (a, p) -> CharSeq.of(a).replaceAll(p, "").isEmpty();

}
