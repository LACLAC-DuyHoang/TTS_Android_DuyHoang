package DataStructuresAndAlgorithms

/*
*    Map: dùng để lưu trữ dữ liệu theo dang key -> value
*       VD: Mã sinh viên -> Tên: SV01 -> Nam
*    - key là duy nhất, value có thể trùng
*    - Có 2 loại map
*       + mapOf(): không sửa
*       + mutableMapOf() : có thể sửa
* */

fun main(){
    // mapOf()
    val student = mapOf(
        "SV01" to "Nam",
        "SV02" to "Hoàng",
        "SV03" to "Hương",
    )
    println(student["SV01"])

    //mutableMapOf()
    val students = mutableMapOf<String,String>()
    students["SV01"]="Nam"
    students["SV02"]="Lan"
    students["SV03"]="An"

    students["SV02"]="Bình"// sửa
    students.remove("SV01")// xóa
    // duyệt mảng
    for((key,value) in students){
        println("$key : $value")
    }
}