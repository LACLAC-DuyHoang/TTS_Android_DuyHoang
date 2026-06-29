package DataStructuresAndAlgorithms

/*
*    Sorting (Sắp xếp) là quá trình sắp xếp các phần tử trong một tập dữ liệu theo một thứ tự nhất định.
*     - Thông thường sẽ sắp xếp theo:
*       + Tăng dần (Ascending)
*       + Giảm dần (Descending)
*       + Theo bảng chữ cái A → Z
*       + Theo nhiều điều kiện (ví dụ: GPA giảm dần, nếu bằng nhau thì tên tăng dần)
*     - Dùng khi muốn:
*       + Hiển thị dữ liệu đẹp hơn
*       + Tìm kiếm nhanh hơn
*       + Thống kê
*       + Lọc dữ liệu
*       + Xếp hạng
*
* */

fun main() {
    val numbers = listOf(5, 2, 9, 1, 7)
    println(numbers.sorted())// sorted: sắp xếp tăng dần
    println(numbers.sortedDescending()) //sortedDescending: sắp xếp giảm dần
    // nếu là string thì sắp xếp thep bảng chữ cái
    val names = listOf("Lan", "An", "Minh", "Hoa")
    println(names.sorted())

    // sortedBy(): sắp xếp tăng dần theo thuộc tính
    // sort() Khác với sorted():
    // sorted(): Tạo List mới / sort(): Sắp xếp trực tiếp List cũ
    val list = mutableListOf(5,4,3)
    val newList = list.sorted()
    println(list)
    println(newList)// list cũ không đổi

}