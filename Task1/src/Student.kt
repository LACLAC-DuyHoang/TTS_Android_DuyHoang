//Class giống như bản thiết kế dùng để tạo ra các Object.
//Class không phải đối tượng thật.Nó chỉ mô tả: Một đối tượng sẽ có gì.
class Student {
    //Property là dữ liệu của Object.(ở đây name và age là Property)
    var name: String = ""
    var age : Int = 0

    //Method là Function nằm bên trong Class.
    fun study(){
        println("$name đang học")
    }
}