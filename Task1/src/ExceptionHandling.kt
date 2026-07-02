/* Exception Handling : là lỗi xảy ra trong quá trình chương trình đang chạy (Runtime)
   làm chương trình bị dừng nếu không được xử lý.

    Khác nhau giữa Exception và Error
        Error	               Exception
    Lỗi nghiêm trọng	    Lỗi có thể xử lý
    Thường do JVM	        Thường do dữ liệu người dùng
    Không nên bắt	        Có thể bắt bằng try-catch


  Exception là một class. các Exception thường gặp
    ArithmeticException	        Chia cho 0
    NumberFormatException	    Chuyển "abc" thành số
    NullPointerException	    Truy cập đối tượng null
    IndexOutOfBoundsException	Truy cập phần tử ngoài phạm vi mảng hoặc danh sách
    IllegalArgumentException	Tham số truyền vào không hợp lệ
    IOException	                Đọc/ghi file hoặc luồng dữ liệu gặp lỗi
 */
fun main(){
    try {
        val a = 10
        val b = 0

        println(a / b)

    } catch (e: Exception) {   //e chính là đối tượng Exception.

        println("Có lỗi xảy ra.")
    }finally {
        println("Đóng file") // finally -> Có những đoạn code luôn phải chạy, dù có lỗi hay không ta dùng finally.
    }

    println("Chương trình vẫn chạy.")

// throw dùng để để chủ động ném ra một ngoại lệ (Exception) khi chương trình gặp phải một tình huống lỗi hoặc một điều kiện không hợp lệ mà bạn đã dự liệu trước
// cú pháp: throw IllegalArgumentException("Thông báo lỗi cụ thể ở đây")
    val age = -5
    if (age < 0) {
        throw IllegalArgumentException("Tuổi không hợp lệ")
    }
}