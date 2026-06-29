package OOP.Inheritance

class Student(
    name: String,
    age: Int,
    var major: String
) : Person(name, age){
    // sử dụng override để ghi đè phương thức
    override fun Display(){
        // Supper: Gọi hàm của lớp cha,Truy cập thuộc tính của lớp cha
        super.Display()
        println("Major: $major")
    }
}