package DataStructuresAndAlgorithms
/*
*    - Array(Mảng): là tập hợp nhiều phần tử cùng kiểu dữ liệu, được lưu trực tiếp trong bộ nhớ
*       + Mỗi phần tử đều có 1 chỉ số (index)
*       + Index luôn bắt đầu từu 0
*    - Dùng khi :
*       + Biết trc số lượng phần tử
*       + ít thay đổi kích thước
* */

// Khai báo:
fun main(){
    val number =  arrayOf(1,2,3,4,5)
    val names = arrayOf("An","Bình","Lan")
// tạo mảng có kích thước cố định
    val arr = Array(5){0}
// truy cập
    println(names[1])
//thay đổi giá trị
    names[1] = "Nam"
    println(names.joinToString())
// joinToString nếu bạn không truyền bất kỳ tham số nào
// thì nó sẽ kết nối các phần tử lại với nhau và ngăn cách chúng bằng dấu phẩy và một khoảng trắng

// Duyệt mảng
    for(item in names){
        println(item)
    }
    //
}
