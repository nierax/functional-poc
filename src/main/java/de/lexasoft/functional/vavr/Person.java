/**
 * 
 */
package de.lexasoft.functional.vavr;

/**
 * 
 */
public class Person {

  public final String name;
  public final int age;
  public final Address address;

  public Person(String name, int age, Address address) {
    this.name = name;
    this.age = age;
    this.address = address;
  }

  @Override
  public String toString() {
    return "Person(" + name + ", " + age + ")";
  }

}
