/**
 * 
 */
package de.lexasoft.functional.vavr;

import de.lexasoft.functional.vavr.Violation.Severity;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * 
 */
public class PersonValidator {

  private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
  private static final int MIN_AGE = 0;

  public Validation<Seq<Violation>, Person> validatePerson(String name, int age, Address address) {
    return Validation.combine(validateName(name), validateAge(age)).ap((n, a) -> new Person(n, a, address));
  }

  private Validation<Violation, String> validateName(String name) {
    return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty() //
        ? Validation.valid(name) //
        : Validation.invalid( //
            Violation.of("PERSON_NAME_INVALID", //
                "Name contains invalid characters: '" + seq.distinct().sorted() + "'", //
                Severity.FATAL))); //
  }

  private Validation<Violation, Integer> validateAge(int age) {
    return age < MIN_AGE //
        ? Validation.invalid(Violation.of("PERSON_AGE_INVALID", "Age must be at least " + MIN_AGE, Severity.ERROR)) //
        : Validation.valid(age); //
  }

}