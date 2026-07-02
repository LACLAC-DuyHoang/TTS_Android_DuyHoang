package OOP.Encapsulation

//Encapsulation là Gom dữ liệu và các hàm xử lý dữ liệu đó vào cùng 1 class, đồng thời che giấu dữu liệu không cho bên ngoài truy cập trực tiếp. (bảo vệ dữu liệu)
//-dùng private
class BankAccount {
    // Thuộc tính (được đóng gói bằng private)
    private var balance = 1000
    //Nếu muốn thay đổi giá trị phải gọi hàm deposit
    fun deposit(amount:Int){
        balance += amount
    }

    fun getBalance():Int{
        return balance
    }
}