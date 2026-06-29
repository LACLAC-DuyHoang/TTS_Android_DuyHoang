package DataStructuresAndAlgorithms

/*
*    List: là tập hợp các phần tử được sắp xếp theo thứ tự
*    - Có thể thêm, sửa, xóa (nếu dùng MutableList)
*    - có hai loại list
*       + Immutable List: không được sửa
*       + Mutable List: có thể thay đổi
 */


fun main(){
    //Immutable List: không được sửa
    val fruits = listOf(
        "Táo",
        "Cam",
        "Chuối"
    )
    //Mutable List: có thể thay đổi
    val students = mutableListOf(
        "An",
        "Bình"
    )
    students.add("Lan") // thêm
    students.remove("Bình")//xóa
    students[0]="Nam" //sửa
    for(student in students){
        println(student)
    }
}
