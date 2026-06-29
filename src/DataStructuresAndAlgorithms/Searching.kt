package DataStructuresAndAlgorithms

/*
*    Searching (Tìm kiếm): là quá trình tìm một phần tử trong tập dữ liệu.
*     - Các cách tìm kiếm
*       + contains(): Kiểm tra có tồn tại không.
*       + in : Kiểm tra có tồn tại không nhưng ngắn hơn
*       + indexOf(): Trả về vị trí ucar phần tử
*       + find(): tìm phần tử đàu tiên thỏa mãn điều kiện, trả về null nếu không tìm thấy
*       + first():tìm phần tử đàu tiên thỏa mãn điều kiện, sẽ ném ra ngoại lệ (NoSuchElementException) nếu không có phần tử nào thỏa điều kiện
*       + filter(): Lấy tất cả phần tử thỏa điều kiện.
* */
fun main() {
    val numbers = listOf(10,15,22,30)
    println(numbers.contains(6))//Kiểm tra có tồn tại không.
    println(6 in numbers)// cũng vậy nhưng ngắn hơn
    println(numbers.indexOf(30))// trả về vị trí
    // tìm phần tử thoa mãn điều kiện >20
    val result = numbers.find {
        it > 20
    }
    println(result)
    println(numbers.first { it > 40 })//lỗi
    //Lấy tất cả phần tử thỏa điều kiện.
    val result2 = numbers.filter { it >= 30 }
    println(result)

}