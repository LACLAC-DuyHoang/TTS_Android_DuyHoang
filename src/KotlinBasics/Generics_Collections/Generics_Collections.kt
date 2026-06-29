package KotlinBasics.Generics_Collections

class Box<T>(
    var value: T
) {
    fun openBox() {
        println("Món quà trong hộp là: $value")
    }
}

fun main() {

    // Truyền kiểu dữ liệu String vào hộp
    val box = Box<String>("Một chiếc gấu bông 🧸")
    box.openBox()

    // Truyền kiểu dữ liệu Int vào hộp
    val numberBox = Box<Int>(2026)
    numberBox.openBox()


// Collections
    // 1. listOf (Không thể thay đổi)
    val danhSachTraiCay = listOf("Táo", "Chuối", "Cam")
    println("Trái cây đầu tiên: ${danhSachTraiCay[0]}")

    // 2. MutableList (Có thể thay đổi thêm, sửa, xóa)
    val danhSachSinhVien = mutableListOf("An", "Bình", "Cường")
    danhSachSinhVien.add("Dũng") // Thêm phần tử mới hợp lệ
    danhSachSinhVien.remove("Bình") // Xóa phần tử hợp lệ

    println("Danh sách sinh viên sau khi cập nhật: $danhSachSinhVien")

}