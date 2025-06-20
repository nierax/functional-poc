/**
 * 
 */
package de.lexasoft.functional.vavr;

import de.lexasoft.functional.vavr.Address.Countries;
import de.lexasoft.functional.vavr.Violation.Severity;
import io.vavr.collection.CharSeq;
import io.vavr.control.Validation;

/**
 * 
 */
public class AdressValidator {

  private static final String VALID_ROAD_CHARS = "[a-zA-Z \\.]";
  private static final String VALID_NR_CHARS = "[0-9a-zA-Z- ]";
  private static final String VALID_ZIP_CHARS = "[0-9A-Z- ]";
  private static final String VALID_CITY_CHARS = "[a-zA-Z- ]";

  private Validation<Violation, String> validateAttributeValue(String pattern, String attribute, String message,
      String id) {
    return CharSeq.of(attribute).replaceAll(pattern, "").transform(seq -> seq.isEmpty() //
        ? Validation.valid(attribute) //
        : Validation.invalid( //
            Violation.of(id, //
                message + " '" + seq.distinct().sorted() + "'", //
                Severity.ERROR))); //
  }

  public Validation<Violation, String> validateRoad(String road) {
    return validateAttributeValue(VALID_ROAD_CHARS, road, "Road contains invalid characters: ", "ADR_ROAD_INVALID");
  }

  public Validation<Violation, String> validateNr(String nr) {
    return validateAttributeValue(VALID_NR_CHARS, nr, "Number contains invalid characters: ", "ADR_NR_INVALID");
  }

  public Validation<Violation, String> validateZIP(String zip) {
    return validateAttributeValue(VALID_ZIP_CHARS, zip, "ZIP contains invalid characters: ", "ADR_ZIP_INVALID");
  }

  public Validation<Violation, String> validateCity(String city) {
    return validateAttributeValue(VALID_CITY_CHARS, city, "City contains invalid characters: ", "ADR_CITY_INVALID");
  }

  public Validation<Violation, Countries> validateCountries(String country) {
    try {
      return Validation.valid(Countries.valueOf(country));
    } catch (IllegalArgumentException ex) {
      return Validation
          .invalid(Violation.of("ADR_COUNTRY_INVALID", "Country was not found: " + country, Severity.ERROR));
    }

  }

}
