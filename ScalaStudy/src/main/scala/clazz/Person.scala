package clazz

import scala.beans.BeanProperty

class Person(var name: String, num: Int) extends Enumeration{
  
  outer=>class internalPerson(){
    outer.age=10
  }
  
  
  private var privateage = 0
  def age = privateage
  def age_=(newValue: Int) { if (newValue > privateage) privateage = newValue }
  
  
  @BeanProperty
  var x = "beanField"
  

}

object Person extends App {
  var person = new Person("william", 100);
  person.age = 100
  person.age = 99
  person.name = "willam2"
  println(person.age)
}