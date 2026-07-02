
fun main(){
    hello()
    hi("Android")
    println(sum(1,2) )

    //Object chính là một đối tượng được tạo từ Class.
    var student = Student()
    student.name = "Hoang"
    student.age = 18
    println(student.name +" " +  student.age)
    //sử dụng method trong class
    student.study()

//    Constructor là một hàm đặc biệt được gọi ngay khi Object được tạo.
//    Nó dùng để khởi tạo dữ liệu ban đầu cho Object.
    val student2 = Student2("Hoang",22)//thì Constructor được gọi.

    //khai báo object (Singleton)
    Database.connect()

}
//hàm không tham số
fun hello(){
    println("Hello")
}

//hàm có tham số nhưng không có dữ liệu trả về
fun hi(name: String){
    println(name)
}

// hàm có tham số và có dữ liệu trả về
fun sum(a: Int, b: Int): Int {
    return a + b
}
//hoặc fun sum(a:Int,b:Int)=a+b


