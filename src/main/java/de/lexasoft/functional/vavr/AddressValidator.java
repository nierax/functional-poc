/**
 * 
 */
package de.lexasoft.functional.vavr;

import static de.lexasoft.functional.vavr.Validations.allowedCharacters;
import static de.lexasoft.functional.vavr.Validations.maxLength;

import de.lexasoft.functional.vavr.Address.Countries;
import de.lexasoft.functional.vavr.Violation.Severity;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * 
 */
public class AddressValidator {

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

  public Validation<Seq<Violation>, Address> validateAddress(String road, String nr, String zip, String city,
      String country) {
    return Validation.combine(//
        validateRoad(road), //
        validateNr(nr), //
        validateZIP(zip), //
        validateCity(city), //
        validateCountries(country))//
        .ap(Address::of);
  }

  public Validation<Violation, String> validateRoad(String road) {
    return validateAttributeValue(VALID_ROAD_CHARS, road, "Road contains invalid characters: ", "ADR_ROAD_INVALID");
  }

  public Validation<Violation, String> validateNr(String nr) {
    return validateAttributeValue(VALID_NR_CHARS, nr, "Number contains invalid characters: ", "ADR_NR_INVALID");
  }

  public Validation<Violation, String> validateZIP(String zip) {
    return allowedCharacters.test(zip, VALID_ZIP_CHARS) && maxLength.test(zip, 7)//
        ? Validation.valid(zip)//
        : Validation.invalid(Violation.of("ADR_ZIP_INVALID", "ZIP is not valid", Severity.ERROR));
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
