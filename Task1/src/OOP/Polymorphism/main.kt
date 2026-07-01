package OOP.Polymorphism

import OOP.Encapsulation.BankAccount
import OOP.Inheritance.Student

fun main(args : Array<String>) {

    //tạo các object
    val dog = Dog()
    val cat = Cat()

    dog.sound()
    cat.sound()
}