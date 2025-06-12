/**
 * 
 */
package de.lexasoft.objectoriented;

/**
 * 
 */
public class Circle {

  private final Double diameter;

  private Circle(Double diameter) {
    this.diameter = diameter;
  }

  public final static Circle ofDiameter(Double diameter) {
    return new Circle(diameter);
  }

  public final static Circle ofRadius(Double radius) {
    return new Circle(radius * 2);
  }

  public Double getDiameter() {
    return diameter;
  }

  public Double getRadius() {
    return diameter / 2;
  }

  public Double getFerence() {
    return Math.PI * diameter;
  }

  public Double getArea() {
    return Math.PI * Math.pow(getRadius(), 2);
  }

}
