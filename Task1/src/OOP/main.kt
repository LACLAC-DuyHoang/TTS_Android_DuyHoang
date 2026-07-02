package OOP

import OOP.Encapsulation.BankAccount
import OOP.Inheritance.Student

//Encapsulation là Gom dữ liệu và các hàm xử lý dữ liệu đó vào cùng 1 class, đồng thời che giấu dữu liệu không cho bên ngoài truy cập trực tiếp. (bảo vệ dữu liệu)
//-dùng private

fun main(args : Array<String>) {
    //tạo object
    val bankAccount = BankAccount()
    // xem số dư ban đầu
    println("Ban đầu: ${bankAccount.getBalance()}")
    // nạp tiền
    bankAccount.deposit(500)
    // xem số dư sau khi nạp
    println("Sau khi nạp: ${bankAccount.getBalance()}")
    println()

    //Kế thừa
    val student = Student("Hoàng", 20, "Kỹ thuật phần mềm")
    student.Display()

}