package OOP.Inheritance

// Để cho phép một class được kế thừa, bạn phải thêm từ khóa open trước class cha đó.
open class Person (
    var name : String ,
    var age: Int
){
    open fun Display(){
        println("Tên: $name")
        println("Tuổi: $age")
    }
}