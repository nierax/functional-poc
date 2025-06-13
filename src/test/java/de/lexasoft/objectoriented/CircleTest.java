package de.lexasoft.objectoriented;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CircleTest {

  @BeforeEach
  void setUp() throws Exception {
  }

  static Stream<Arguments> testRadius() {
    return Stream.of(//
        Arguments.of(3.0, 1.5), //
        Arguments.of(4.0, 2.0), //
        Arguments.of(0, 0));
  }

  @ParameterizedTest
  @MethodSource
  void testRadius(double diameter, double expected) {
    Circle cut = Circle.ofDiameter(diameter);
    assertEquals(expected, cut.getRadius(), 0.0000001);
  }

  static Stream<Arguments> testFerence() {
    return Stream.of(//
        Arguments.of(3.0, 9.4247779607693797), //
        Arguments.of(4.0, 12.5663706143591729), //
        Arguments.of(0, 0));
  }

  @ParameterizedTest
  @MethodSource
  void testFerence(double diameter, double expected) {
    Circle cut = Circle.ofDiameter(diameter);
    assertEquals(expected, cut.getFerence(), 0.0000001);
  }

  static Stream<Arguments> testArea() {
    return Stream.of(//
        Arguments.of(3.0, 7.0685834705770347), //
        Arguments.of(4.0, 12.5663706143591729), //
        Arguments.of(0, 0));
  }

  @ParameterizedTest
  @MethodSource
  void testArea(double diameter, double expected) {
    Circle cut = Circle.ofDiameter(diameter);
    assertEquals(expected, cut.getArea());
  }

}
