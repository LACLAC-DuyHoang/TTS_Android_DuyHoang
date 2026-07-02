//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //Biến (Variable) là một vùng nhớ trong RAM được dùng để lưu trữ dữ liệu.
    /*
       - sử dụng 2 từ khóa chính là val hoặc var
         + val(value): dùng để khai báo nhưunxg biến không thể thay đổi giá trị sau khi đã đc gán
         + var (variable): dùng để khai báo các biến được phép thay đổi giá trị trong quá trình chạy chương trình
    */

    //Cơ chế tự động suy luận kiểu dữ liệu
    val website = "website"// Tự hiểu là kiểu String
    var year = 2026         // Tự hiểu là kiểu Int
    val pi = 3.14           // Tự hiểu là kiểu Double
    println(website)

    /* Khai báo biến kèm kiểu dữ liệu cụ thể
        + Cú pháp: val/var tên_biến: Kiểu_Dữ_Liệu = giá_trị
    */
    val score: Int =100
    var message : String = "Hello World"
    val IsReady : Boolean = true

    val role: String
    role = "Admin"

    //Nếu muốn 1 biến có thể nhận giá trị null -> thêm dấu ? ngay sau kiểu dữu liệu
    var nullableName: String? = "Android"
    nullableName = null // Hợp lệ!
    println(nullableName)
}

