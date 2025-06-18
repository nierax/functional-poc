package de.lexasoft.functional.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

class PersonValidatorTest {

  PersonValidator cut;

  @BeforeEach
  void setUp() throws Exception {
    cut = new PersonValidator();
  }

  @Test
  void testValidatePerson_valid() {
    Validation<Seq<String>, Person> valid = cut.validatePerson("John Doe", 30);
    assertTrue(valid.isValid());
    Person person = valid.get();
    assertEquals("John Doe", person.name);
    assertEquals(30, person.age);
  }

  @Test
  void testValidatePerson_invalid_both_attributes() {
    Validation<Seq<String>, Person> invalid = cut.validatePerson("John? Doe!4", -1);
    assertFalse(invalid.isValid());
    assertEquals("Name contains invalid characters: '!4?'", invalid.getError().get(0));
    assertEquals("Age must be at least 0", invalid.getError().get(1));
  }

}
