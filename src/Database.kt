//object là một từ khóa (keyword) trong Kotlin dùng để khai báo một đối tượng (Object) duy nhất của một lớp.
//Là một đối tượng duy nhất được Kotlin tạo sẵn, Chỉ có một object duy nhất
object Database {
    //Singleton nghĩa là Trong toàn bộ chương trình chỉ có 1 object duy nhất.
    fun connect() {
        println("Connected")
    }
}