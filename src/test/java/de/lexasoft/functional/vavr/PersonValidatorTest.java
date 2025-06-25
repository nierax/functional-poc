package de.lexasoft.functional.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lexasoft.functional.vavr.Address.Countries;
import de.lexasoft.functional.vavr.Violation.Severity;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

class PersonValidatorTest {

  PersonValidator cut;
  Address address;

  @BeforeEach
  void setUp() throws Exception {
    cut = new PersonValidator();
    address = Address.of("Main road", "10", "D-10000", "Berlin", Countries.GERMANY);
  }

  @Test
  void testValidatePerson_valid() {
    Validation<Seq<Violation>, Person> valid = cut.validatePerson("John Doe", 30, address);
    assertTrue(valid.isValid());
    Person person = valid.get();
    assertEquals("John Doe", person.name);
    assertEquals(30, person.age);
  }

  @Test
  void testValidatePerson_invalid_both_attributes() {
    Validation<Seq<Violation>, Person> invalid = cut.validatePerson("John? Doe!4", -1, address);
    assertFalse(invalid.isValid());
    assertEquals(2, invalid.getError().length());

    Violation vio0 = invalid.getError().get(0);
    assertEquals("PERSON_NAME_INVALID", vio0.id);
    assertEquals("Name contains invalid characters: '!4?'", vio0.message);
    assertEquals(Severity.FATAL, vio0.severity);

    Violation vio1 = invalid.getError().get(1);
    assertEquals("PERSON_AGE_INVALID", vio1.id);
    assertEquals("Age must be at least 0", vio1.message);
    assertEquals(Severity.ERROR, vio1.severity);
  }

  @Test
  void testValidatePerson_invalid_age() {
    Validation<Seq<Violation>, Person> invalid = cut.validatePerson("John Doe", -10, address);
    assertFalse(invalid.isValid());
    assertEquals(1, invalid.getError().length());

    Violation vio = invalid.getError().get();
    assertEquals("PERSON_AGE_INVALID", vio.id);
    assertEquals("Age must be at least 0", vio.message);
    assertEquals(Severity.ERROR, vio.severity);
  }

}
