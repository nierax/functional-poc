package de.lexasoft.objectoriented;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {

  Circle cut;

  @BeforeEach
  void setUp() throws Exception {
    cut = Circle.ofDiameter(3d);
  }

  @Test
  void testRadius() {
    assertEquals(1.5d, cut.getRadius());
  }

  @Test
  void testFerence() {
    assertEquals(9.42477796076938, cut.getFerence());
  }

  @Test
  void testArea() {
    assertEquals(7.0685834705770345, cut.getArea());
  }

}
