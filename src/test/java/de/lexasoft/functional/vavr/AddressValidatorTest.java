/**
 * 
 */
package de.lexasoft.functional.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import de.lexasoft.functional.vavr.Address.Countries;

/**
 * 
 */
class AddressValidatorTest {

  private AddressValidator cut;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    cut = new AddressValidator();
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateRoad(java.lang.String)}.
   */
  @ParameterizedTest
  @ValueSource(strings = { "Bahnhofstr.", "Hauptstr." })
  void testValidateRoad_Valid(String road) {
    assertEquals(road, cut.validateRoad(road).get());
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateRoad(java.lang.String)}.
   */
  @ParameterizedTest
  @ValueSource(strings = { "Ma!-n", "?ß+" })
  void testValidateRoad_Invalid(String road) {
    assertEquals("ADR_ROAD_INVALID", cut.validateRoad(road).getError().id);
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateNr(java.lang.String)}.
   */
  @ParameterizedTest
  @ValueSource(strings = { "36", "36a", "36 a", "1-5" })
  void testValidateNr_Valid(String nr) {
    assertEquals(nr, cut.validateNr(nr).get());
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateNr(java.lang.String)}.
   */
  @ParameterizedTest
  @ValueSource(strings = { "36:a", ".-", "36@a" })
  void testValidateNr_Invalid(String nr) {
    assertEquals("ADR_NR_INVALID", cut.validateNr(nr).getError().id);
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateZIP(java.lang.String)}.
   */
  @ParameterizedTest
  @ValueSource(strings = { "D-72202", "72202", "D14389" })
  void testValidateZIP_Valid(String zip) {
    assertEquals(zip, cut.validateZIP(zip).get());
  }

  @ParameterizedTest
  @ValueSource(strings = { "d-72202", ".-#", "u", "D-72202AS" })
  void testValidateZIP_Invalid(String zip) {
    assertEquals("ADR_ZIP_INVALID", cut.validateZIP(zip).getError().id);
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateCity(java.lang.String)}.
   */
  @ParameterizedTest
  @ValueSource(strings = { "Stuttgart", "Kopenhagen", "Munich" })
  void testValidateCity_Valid(String city) {
    assertEquals(city, cut.validateCity(city).get());
  }

  @ParameterizedTest
  @ValueSource(strings = { "':;", "Me!To", "München" })
  void testValidateCity_Invalid(String city) {
    assertEquals("ADR_CITY_INVALID", cut.validateCity(city).getError().id);
  }

  static Stream<Arguments> testValidateCountries_Valid() {
    return Stream.of(//
        Arguments.of("GERMANY", Countries.GERMANY), //
        Arguments.of("AUSTRIA", Countries.AUSTRIA), //
        Arguments.of("FRANCE", Countries.FRANCE), //
        Arguments.of("DANMARK", Countries.DANMARK), //
        Arguments.of("SWITZERLAND", Countries.SWITZERLAND));
  }

  /**
   * Test method for
   * {@link de.lexasoft.functional.vavr.AddressValidator#validateCountries(java.lang.String)}.
   */
  @ParameterizedTest
  @MethodSource
  void testValidateCountries_Valid(String value, Countries expected) {
    assertEquals(expected, cut.validateCountries(value).get());
  }

  @ParameterizedTest
  @ValueSource(strings = { "DEUTSCH", "FRANKREICH", "german" })
  void testValidateCountries_Invalid(String value) {
    assertEquals("ADR_COUNTRY_INVALID", cut.validateCountries(value).getError().id);
  }

  @Test
  void testValidateAddress_Valid() {
    Address adr = cut.validateAddress("Mainroad", "15", "71000", "Hometown", "GERMANY").get();
    assertNotNull(adr);
    assertEquals("Mainroad", adr.road);
    assertEquals("15", adr.nr);
    assertEquals("71000", adr.zip);
    assertEquals("Hometown", adr.city);
    assertEquals(Countries.GERMANY, adr.country);
  }
}
